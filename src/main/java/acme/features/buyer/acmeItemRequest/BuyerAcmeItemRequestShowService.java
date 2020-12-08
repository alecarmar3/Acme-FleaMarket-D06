
package acme.features.buyer.acmeItemRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItemRequest;
import acme.entities.roles.Buyer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class BuyerAcmeItemRequestShowService implements AbstractShowService<Buyer, AcmeItemRequest> {

	@Autowired
	private BuyerAcmeItemRequestRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItemRequest> request) {
		assert request != null;

		Boolean isMine = this.repository.findMyAcmeItemRequests(request.getPrincipal().getActiveRoleId()).contains(this.repository.findOneById(request.getModel().getInteger("id")));

		return isMine;
	}

	@Override
	public void unbind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationDate", "status", "justification", "quantity", "notes", "buyer.userAccount.username", "acmeItem.title");
	}

	@Override
	public AcmeItemRequest findOne(final Request<AcmeItemRequest> request) {
		assert request != null;

		AcmeItemRequest result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
