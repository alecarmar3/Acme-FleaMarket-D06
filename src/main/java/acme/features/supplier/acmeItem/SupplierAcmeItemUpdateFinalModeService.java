
package acme.features.supplier.acmeItem;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.SpecificationSheet;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierAcmeItemUpdateFinalModeService implements AbstractUpdateService<Supplier, AcmeItem> {

	@Autowired
	SupplierAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;

		AcmeItem requested = this.repository.findOneById(request.getModel().getInteger("id"));

		Boolean isMine = requested.getSupplier().getId() == request.getPrincipal().getActiveRoleId();

		Boolean isNotFinalMode = this.repository.findFinalMode(request.getModel().getInteger("id")).equals(false);

		return isNotFinalMode && isMine;
	}

	@Override
	public void bind(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);

		request.bind(entity, errors, "updateDate");
	}

	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "description", "photo", "additionalInformation", "finalMode", "isNew");
	}

	@Override
	public AcmeItem findOne(final Request<AcmeItem> request) {
		assert request != null;

		AcmeItem result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int investmentRoundId = entity.getId();
		Boolean showFinalMode = this.repository.findFinalMode(investmentRoundId);

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		String categoryOfThisAcmeItem = request.getModel().getAttribute("category").toString();

		String ticker = entity.getTicker();
		AcmeItem acmeItemWithTicker = this.repository.findAcmeItemByTicker(ticker);

		int acmeItemId = request.getModel().getInteger("id");

		Collection<SpecificationSheet> specificationSheetsByAcmeItem = this.repository.findSpecificationSheetsByAcmeItem(acmeItemId);

		if (!errors.hasErrors("ticker")) {

			Boolean categoryLetters = false;

			if (categoryOfThisAcmeItem.length() >= 3) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().subSequence(0, 3));
			} else if (categoryOfThisAcmeItem.length() == 2) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().concat("X"));
			} else if (categoryOfThisAcmeItem.length() == 1) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().concat("XX"));
			}

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			errors.state(request, categoryLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			if (acmeItemWithTicker != null) {
				Boolean acmeItemWithTickerIsThis = acmeItemWithTicker.equals(this.repository.findOneById(entity.getId()));
				errors.state(request, acmeItemWithTickerIsThis, "ticker", "default.error.already-in-use", entity.getTicker());
			}
		}

		if (!errors.hasErrors("title")) {
			boolean isNotSpam = !config.isSpam(entity.getTitle());
			errors.state(request, isNotSpam, "title", "default.error.spam");
		}

		if (!errors.hasErrors("description")) {
			boolean isNotSpam = !config.isSpam(entity.getDescription());
			errors.state(request, isNotSpam, "description", "default.error.spam");
		}

		if (!request.getModel().getAttribute("photo").toString().isEmpty() && !errors.hasErrors("photo")) {
			boolean isNotSpam = !config.isSpam(entity.getPhoto());
			errors.state(request, isNotSpam, "photo", "default.error.spam");
		}

		if (!request.getModel().getAttribute("additionalInformation").toString().isEmpty() && !errors.hasErrors("additionalInformation")) {
			boolean isNotSpam = !config.isSpam(entity.getAdditionalInformation());
			errors.state(request, isNotSpam, "additionalInformation", "default.error.spam");
		}

		if (!errors.hasErrors("finalMode")) {
			Boolean hasSpecificationSheet = false;
			Boolean uniqueIndexers = true;
			if (!specificationSheetsByAcmeItem.isEmpty()) {
				uniqueIndexers = specificationSheetsByAcmeItem.stream().map(x -> x.getIndexer()).filter(i -> Collections.frequency(specificationSheetsByAcmeItem, i) > 1).collect(Collectors.toList()).isEmpty();
				hasSpecificationSheet = true;
			}

			errors.state(request, hasSpecificationSheet, "finalMode", "default.error.does-not-have-specification-sheet", entity.getFinalMode());
			errors.state(request, uniqueIndexers, "finalMode", "default.error.not-unique-indexers", entity.getFinalMode());
			errors.state(request, !showFinalMode, "finalMode", "default.error.updating-unavailable", entity.getFinalMode());
		}

	}

	@Override
	public void update(final Request<AcmeItem> request, final AcmeItem entity) {
		assert request != null;
		assert entity != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);
		entity.setFinalMode(true);

		this.repository.save(entity);
	}

}
