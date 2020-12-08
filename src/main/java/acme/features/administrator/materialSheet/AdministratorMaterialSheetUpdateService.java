
package acme.features.administrator.materialSheet;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.MaterialSheet;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorMaterialSheetUpdateService implements AbstractUpdateService<Administrator, MaterialSheet> {

	@Autowired
	AdministratorMaterialSheetRepository repository;


	@Override
	public boolean authorise(final Request<MaterialSheet> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MaterialSheet> request, final MaterialSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MaterialSheet> request, final MaterialSheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "updateDate", "providerName", "homePage", "stars");
	}

	@Override
	public MaterialSheet findOne(final Request<MaterialSheet> request) {
		assert request != null;

		MaterialSheet result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<MaterialSheet> request, final MaterialSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<MaterialSheet> request, final MaterialSheet entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(moment);

		this.repository.save(entity);
	}
}
