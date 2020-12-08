
package acme.features.administrator.figment;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Figment;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorFigmentCreateService implements AbstractCreateService<Administrator, Figment> {

	@Autowired
	AdministratorFigmentRepository repository;


	@Override
	public boolean authorise(final Request<Figment> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Figment> request, final Figment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Figment> request, final Figment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "inventorName", "textPiece", "priceMin", "priceMax");
	}

	@Override
	public Figment instantiate(final Request<Figment> request) {
		Figment result;

		result = new Figment();

		return result;
	}

	@Override
	public void validate(final Request<Figment> request, final Figment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("priceMin")) {
			Boolean priceMinEuros = entity.getPriceMin().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, priceMinEuros, "priceMin", "default.error.wrong-currency", entity.getPriceMin());
		}

		if (!errors.hasErrors("priceMax")) {
			Boolean priceMaxEuros = entity.getPriceMax().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, priceMaxEuros, "priceMax", "default.error.wrong-currency", entity.getPriceMax());
		}

		if (!errors.hasErrors("priceMin") && !errors.hasErrors("priceMax")) {
			Boolean lowerThanMax = entity.getPriceMax().getAmount() > entity.getPriceMin().getAmount();
			errors.state(request, lowerThanMax, "priceMin", "administrator.figment.error.max-higher-than-min");
		}

	}

	@Override
	public void create(final Request<Figment> request, final Figment entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		this.repository.save(entity);
	}

}
