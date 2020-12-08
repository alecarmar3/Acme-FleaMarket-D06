
package acme.features.supplier.acmeItemRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.AcmeItemRequest;
import acme.entities.AcmeItemRequestStatus;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierAcmeItemRequestUpdateAcceptService implements AbstractUpdateService<Supplier, AcmeItemRequest> {

	@Autowired
	SupplierAcmeItemRequestRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItemRequest> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		int acmeItemRequestId = request.getModel().getInteger("id");
		AcmeItemRequest acmeItemRequest = this.repository.findOneById(acmeItemRequestId);

		AcmeItem acmeItem = acmeItemRequest.getAcmeItem();

		Boolean thisSupplierIsMe = id == acmeItem.getSupplier().getId();
		Boolean isPending = acmeItemRequest.getStatus().equals(AcmeItemRequestStatus.PENDING);

		return thisSupplierIsMe && isPending;
	}

	@Override
	public void bind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status");
	}

	@Override
	public AcmeItemRequest findOne(final Request<AcmeItemRequest> request) {
		assert request != null;

		AcmeItemRequest result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void validate(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<AcmeItemRequest> request, final AcmeItemRequest entity) {
		assert request != null;
		assert entity != null;

		entity.setStatus(AcmeItemRequestStatus.ACCEPTED);

		this.repository.save(entity);
	}

}
