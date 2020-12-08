
package acme.features.supplier.acmeItem;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierAcmeItemCreateService implements AbstractCreateService<Supplier, AcmeItem> {

	@Autowired
	SupplierAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);
		entity.setUpdateDate(moment);

		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		Supplier auth = this.repository.findSupplierById(authId);

		entity.setSupplier(auth);
		entity.setFinalMode(false);
		entity.setIsNew(false);

		request.bind(entity, errors, "creationDate", "updateDate", "finalMode", "supplier", "isNew");
	}

	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "category", "description", "price", "photo", "additionalInformation");
	}

	@Override
	public AcmeItem instantiate(final Request<AcmeItem> request) {
		AcmeItem result;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();
		Supplier supplier = this.repository.findSupplierById(id);

		result = new AcmeItem();
		result.setSupplier(supplier);
		result.setFinalMode(false);
		result.setIsNew(false);

		return result;
	}

	@Override
	public void validate(final Request<AcmeItem> request, final AcmeItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String categoryOfThisAcmeItem = request.getModel().getAttribute("category").toString();

		Collection<String> tickersInUse = this.repository.findTickersInUse();

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

			Boolean tickerAvailable = !tickersInUse.contains(request.getModel().getAttribute("ticker"));

			errors.state(request, categoryLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			errors.state(request, tickerAvailable, "ticker", "default.error.already-in-use", entity.getTicker());
		}

		if (!errors.hasErrors("price")) {
			Boolean priceEuros = entity.getPrice().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, priceEuros, "price", "default.error.wrong-currency", entity.getPrice());
		}

	}

	@Override
	public void create(final Request<AcmeItem> request, final AcmeItem entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);
		entity.setUpdateDate(moment);

		this.repository.save(entity);
	}

}
