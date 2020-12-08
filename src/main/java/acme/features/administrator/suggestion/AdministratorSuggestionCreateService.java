
package acme.features.administrator.suggestion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Suggestion;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSuggestionCreateService implements AbstractCreateService<Administrator, Suggestion> {

	@Autowired
	AdministratorSuggestionRepository repository;


	@Override
	public boolean authorise(final Request<Suggestion> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Suggestion> request, final Suggestion entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Suggestion> request, final Suggestion entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "textPiece", "email");
	}

	@Override
	public Suggestion instantiate(final Request<Suggestion> request) {
		Suggestion result;

		result = new Suggestion();

		return result;
	}

	@Override
	public void validate(final Request<Suggestion> request, final Suggestion entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Suggestion> request, final Suggestion entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		this.repository.save(entity);
	}

}
