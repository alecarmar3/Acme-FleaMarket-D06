
package acme.features.authenticated.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.News;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedNewsShowService implements AbstractShowService<Authenticated, News> {

	@Autowired
	private AuthenticatedNewsRepository repository;


	@Override
	public boolean authorise(final Request<News> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<News> request, final News entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "category", "headerPicture", "title", "creationDate", "deadline", "body", "newsLinks");
	}

	@Override
	public News findOne(final Request<News> request) {
		assert request != null;

		News result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
