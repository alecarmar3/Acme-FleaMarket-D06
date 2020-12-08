
package acme.features.authenticated.acmeItem;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAcmeItemShowService implements AbstractShowService<Authenticated, AcmeItem> {

	@Autowired
	private AuthenticatedAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;

		Boolean isFinalMode = this.repository.findOneById(request.getModel().getInteger("id")).getFinalMode();

		return isFinalMode;
	}

	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int id = request.getPrincipal().getAccountId();
		Collection<String> roles = this.repository.findUserAccountRoles(id).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());
		Boolean isBuyer = roles.contains("Buyer");
		model.setAttribute("isBuyer", isBuyer);

		int AcmeItemId = request.getModel().getInteger("id");
		model.setAttribute("AcmeItemId", AcmeItemId);

		request.unbind(entity, model, "ticker", "creationDate", "updateDate", "title", "category", "description", "price", "photo", "additionalInformation", "finalMode", "isNew");
	}

	@Override
	public AcmeItem findOne(final Request<AcmeItem> request) {
		assert request != null;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		Date thendate = cal.getTime();

		AcmeItem result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		if (result.getFinalMode().equals(true) && result.getUpdateDate().after(thendate)) {
			result.setIsNew(true);
		}

		return result;
	}

}
