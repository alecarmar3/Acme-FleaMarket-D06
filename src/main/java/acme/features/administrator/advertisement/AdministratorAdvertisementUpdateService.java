
package acme.features.administrator.advertisement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Advertisement;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAdvertisementUpdateService implements AbstractUpdateService<Administrator, Advertisement> {

	@Autowired
	AdministratorAdvertisementRepository repository;


	@Override
	public boolean authorise(final Request<Advertisement> request) {
		assert request != null;

		Date moment = new Date(System.currentTimeMillis() - 1);

		Boolean isDisplayed = this.repository.findOneById(request.getModel().getInteger("id")).getDisplayedUntil().after(moment);

		return isDisplayed;
	}

	@Override
	public void bind(final Request<Advertisement> request, final Advertisement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Advertisement> request, final Advertisement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "picture", "displayedUntil", "updateDate", "textPiece", "smallVolumeDiscount", "averageVolumeDiscount", "largeVolumeDiscount", "smallInterval", "averageInterval", "largeInterval");
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

	@Override
	public void validate(final Request<Advertisement> request, final Advertisement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("displayedUntil")) {
			Boolean isFuture = entity.getDisplayedUntil().after(new Date());
			errors.state(request, isFuture, "displayedUntil", "administrator.advertisement.error.past-deadline", entity.getDisplayedUntil());
		}

		if (!errors.hasErrors("smallVolumeDiscount") && !errors.hasErrors("averageVolumeDiscount")) {
			Boolean lowerThanAverage = entity.getSmallVolumeDiscount() < entity.getAverageVolumeDiscount();
			errors.state(request, lowerThanAverage, "smallVolumeDiscount", "administrator.advertisement.error.average-higher-than-small");
		}

		if (!errors.hasErrors("largeVolumeDiscount") && !errors.hasErrors("averageVolumeDiscount")) {
			Boolean higherThanAverage = entity.getLargeVolumeDiscount() > entity.getAverageVolumeDiscount();
			errors.state(request, higherThanAverage, "largeVolumeDiscount", "administrator.advertisement.error.large-lower-than-average");
		}

	}

	@Override
	public void update(final Request<Advertisement> request, final Advertisement entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(moment);

		this.repository.save(entity);
	}
}
