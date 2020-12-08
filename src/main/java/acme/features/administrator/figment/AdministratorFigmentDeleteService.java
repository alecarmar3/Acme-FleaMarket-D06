
package acme.features.administrator.figment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Figment;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorFigmentDeleteService implements AbstractDeleteService<Administrator, Figment> {

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

		request.bind(entity, errors);
	}
	@Override
	public void unbind(final Request<Figment> request, final Figment entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "inventorName", "textPiece", "priceMin", "priceMax");
	}
	@Override
	public Figment findOne(final Request<Figment> request) {
		assert request != null;
		Figment result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;

	}
	@Override
	public void validate(final Request<Figment> request, final Figment entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Figment> request, final Figment entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}
}
