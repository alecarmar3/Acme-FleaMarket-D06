
package acme.features.supplier.acmeItem;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierAcmeItemShowService implements AbstractShowService<Supplier, AcmeItem> {

	@Autowired
	private SupplierAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;

		AcmeItem requested = this.repository.findOneById(request.getModel().getInteger("id"));

		Boolean isMine = requested.getSupplier().getId() == request.getPrincipal().getActiveRoleId();

		return isMine;
	}

	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int AcmeItemId = request.getModel().getInteger("id");
		boolean isNotRequested = this.repository.findAcmeItemRequestsForThisAcmeItem(AcmeItemId).isEmpty();

		model.setAttribute("AcmeItemId", AcmeItemId);
		model.setAttribute("isNotRequested", isNotRequested);

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
