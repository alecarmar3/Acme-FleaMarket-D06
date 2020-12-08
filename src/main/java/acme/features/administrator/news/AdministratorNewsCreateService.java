
package acme.features.administrator.news;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.News;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNewsCreateService implements AbstractCreateService<Administrator, News> {

	@Autowired
	AdministratorNewsRepository repository;


	@Override
	public boolean authorise(final Request<News> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<News> request, final News entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<News> request, final News entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "category", "headerPicture", "title", "deadline", "body", "newsLinks");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");

		} else {
			request.transfer(model, "accept");
		}
	}

	@Override
	public News instantiate(final Request<News> request) {
		News result;

		result = new News();

		return result;
	}

	@Override
	public void validate(final Request<News> request, final News entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Boolean isFuture = entity.getDeadline().after(new Date());
			errors.state(request, isFuture, "deadline", "administrator.news.error.past-deadline", entity.getDeadline());
		}

		if (!errors.hasErrors("accept")) {
			Boolean isChecked = request.getModel().getBoolean("accept");
			errors.state(request, isChecked, "accept", "administrator.news.error.must-accept");
		}

	}

	@Override
	public void create(final Request<News> request, final News entity) {

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		this.repository.save(entity);
	}

}
