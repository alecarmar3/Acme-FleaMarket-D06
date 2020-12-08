
package acme.features.supplier.acmeItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.SpecificationSheet;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SupplierAcmeItemDeleteService implements AbstractDeleteService<Supplier, AcmeItem> {

	@Autowired
	SupplierAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		int acmeItemId = request.getModel().getInteger("id");

		Boolean isMyAcmeItem = this.repository.findOneById(acmeItemId).getSupplier().getId() == id;

		Boolean isNotRequested = this.repository.findAcmeItemRequestsForThisAcmeItem(acmeItemId).isEmpty();

		return isMyAcmeItem && isNotRequested;

	}

	@Override
	public void bind(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate", "updateDate");
	}
	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "category", "description", "price", "photo", "additionalInformation", "finalMode", "isNew");
	}
	@Override
	public AcmeItem findOne(final Request<AcmeItem> request) {
		assert request != null;
		AcmeItem result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;

	}
	@Override
	public void validate(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<AcmeItem> request, final AcmeItem entity) {
		assert request != null;
		assert entity != null;

		Collection<SpecificationSheet> specificationSheets = this.repository.findSpecificationSheetsByAcmeItem(entity.getId());

		if (!specificationSheets.isEmpty()) {

			for (SpecificationSheet specificationSheet : specificationSheets) {
				this.repository.delete(specificationSheet);
			}

		}

		this.repository.delete(entity);
	}
}
