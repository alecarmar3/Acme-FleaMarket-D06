
package acme.features.authenticated.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Suggestion;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSuggestionShowService implements AbstractShowService<Authenticated, Suggestion> {

	@Autowired
	private AuthenticatedSuggestionRepository repository;


	@Override
	public boolean authorise(final Request<Suggestion> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Suggestion> request, final Suggestion entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "textPiece", "email");
	}

	@Override
	public Suggestion findOne(final Request<Suggestion> request) {
		assert request != null;

		Suggestion result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
