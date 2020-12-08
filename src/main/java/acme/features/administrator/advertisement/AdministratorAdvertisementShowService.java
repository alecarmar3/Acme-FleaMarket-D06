
package acme.features.administrator.advertisement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Advertisement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAdvertisementShowService implements AbstractShowService<Administrator, Advertisement> {

	@Autowired
	private AdministratorAdvertisementRepository repository;


	@Override
	public boolean authorise(final Request<Advertisement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Advertisement> request, final Advertisement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Date moment = new Date(System.currentTimeMillis() - 1);

		model.setAttribute("isDisplayed", entity.getDisplayedUntil().after(moment));

		request.unbind(entity, model, "title", "picture", "creationDate", "updateDate", "displayedUntil", "textPiece", "smallVolumeDiscount", "averageVolumeDiscount", "largeVolumeDiscount", "smallInterval", "averageInterval", "largeInterval");
	}

	@Override
	public Advertisement findOne(final Request<Advertisement> request) {
		assert request != null;

		Advertisement result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
