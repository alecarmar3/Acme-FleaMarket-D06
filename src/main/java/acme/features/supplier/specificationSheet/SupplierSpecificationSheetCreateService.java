
package acme.features.supplier.specificationSheet;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.SpecificationSheet;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierSpecificationSheetCreateService implements AbstractCreateService<Supplier, SpecificationSheet> {

	@Autowired
	SupplierSpecificationSheetRepository repository;


	@Override
	public boolean authorise(final Request<SpecificationSheet> request) {
		assert request != null;

		int id = request.getPrincipal().getAccountId();
		AcmeItem acmeItem = this.repository.findAcmeItemById(request.getModel().getInteger("AcmeItemId"));

		Collection<String> roles = this.repository.findUserAccountRoles(id).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean isFinalMode = acmeItem.getFinalMode();
		Boolean isSupplier = roles.contains("Supplier");

		return !isFinalMode && isSupplier;
	}

	@Override
	public void bind(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!request.isMethod(HttpMethod.GET)) {
			AcmeItem acmeItem = entity.getAcmeItem();
			request.getModel().setAttribute("acmeItem", acmeItem);
		}

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "indexer", "sheetTitle", "sheetDescription", "photo", "acmeItem");
	}

	@Override
	public SpecificationSheet instantiate(final Request<SpecificationSheet> request) {
		SpecificationSheet result;

		AcmeItem acmeItem = this.repository.findAcmeItemById(request.getModel().getInteger("AcmeItemId"));

		result = new SpecificationSheet();
		result.setAcmeItem(acmeItem);

		return result;
	}

	@Override
	public void validate(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("indexer")) {
			int id = request.getModel().getInteger("AcmeItemId");
			Collection<Integer> specificationSheetsIndexersOfAcmeItem = this.repository.findSpecificationSheetsIndexersByAcmeItem(id);
			Boolean isUniqueWithinAcmeItem = !specificationSheetsIndexersOfAcmeItem.contains(entity.getIndexer());
			errors.state(request, isUniqueWithinAcmeItem, "indexer", "default.error.already-in-use", entity.getIndexer());
		}

		if (!errors.hasErrors("sheetTitle")) {
			boolean isNotSpam = !config.isSpam(entity.getSheetTitle());
			errors.state(request, isNotSpam, "sheetTitle", "default.error.spam");
		}

		if (!errors.hasErrors("sheetDescription")) {
			boolean isNotSpam = !config.isSpam(entity.getSheetDescription());
			errors.state(request, isNotSpam, "sheetDescription", "default.error.spam");
		}

	}

	@Override
	public void create(final Request<SpecificationSheet> request, final SpecificationSheet entity) {
		this.repository.save(entity);
	}

}
