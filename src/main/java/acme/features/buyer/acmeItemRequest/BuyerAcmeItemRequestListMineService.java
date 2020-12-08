
package acme.features.buyer.acmeItemRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItemRequest;
import acme.entities.roles.Buyer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class BuyerAcmeItemRequestListMineService implements AbstractListService<Buyer, AcmeItemRequest> {

	@Autowired
	BuyerAcmeItemRequestRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItemRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "status", "acmeItem.title");
	}

	@Override
	public Collection<AcmeItemRequest> findMany(final Request<AcmeItemRequest> request) {
		assert request != null;
		Collection<AcmeItemRequest> result;

		int buyerId = request.getPrincipal().getActiveRoleId();

		result = this.repository.findMyAcmeItemRequests(buyerId);

		return result;
	}

}
