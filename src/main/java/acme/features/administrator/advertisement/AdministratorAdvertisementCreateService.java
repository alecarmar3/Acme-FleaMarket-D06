
package acme.features.administrator.advertisement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Advertisement;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAdvertisementCreateService implements AbstractCreateService<Administrator, Advertisement> {

	@Autowired
	AdministratorAdvertisementRepository repository;


	@Override
	public boolean authorise(final Request<Advertisement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Advertisement> request, final Advertisement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Advertisement> request, final Advertisement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "picture", "displayedUntil", "textPiece", "smallVolumeDiscount", "averageVolumeDiscount", "largeVolumeDiscount", "smallInterval", "averageInterval", "largeInterval");
	}

	@Override
	public Advertisement instantiate(final Request<Advertisement> request) {
		Advertisement result;

		result = new Advertisement();

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
	public void create(final Request<Advertisement> request, final Advertisement entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		this.repository.save(entity);
	}

}
