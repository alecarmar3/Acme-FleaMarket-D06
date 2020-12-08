
package acme.features.supplier.acmeItem;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierAcmeItemUpdateService implements AbstractUpdateService<Supplier, AcmeItem> {

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

		request.unbind(entity, model, "ticker", "title", "category", "description", "price", "photo", "additionalInformation", "isNew");
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

		String categoryOfThisAcmeItem = request.getModel().getAttribute("category").toString();

		String ticker = entity.getTicker();
		AcmeItem acmeItemWithTicker = this.repository.findAcmeItemByTicker(ticker);

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("category")) {
			Boolean isOneOption = config.acmeItemCategoriesToList().contains(entity.getCategory());
			errors.state(request, isOneOption, "category", "This value must fit one of these options:" + config.acmeItemCategoriesToList(), entity.getCategory());
		}

		if (!errors.hasErrors("ticker") && !errors.hasErrors("category")) {

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

		if (!errors.hasErrors("price")) {
			Boolean priceEuros = entity.getPrice().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, priceEuros, "price", "default.error.wrong-currency", entity.getPrice());
		}

	}

	@Override
	public void update(final Request<AcmeItem> request, final AcmeItem entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
