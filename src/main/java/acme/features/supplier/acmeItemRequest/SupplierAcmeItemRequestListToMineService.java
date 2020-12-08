
package acme.features.supplier.acmeItemRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItemRequest;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class SupplierAcmeItemRequestListToMineService implements AbstractListService<Supplier, AcmeItemRequest> {

	@Autowired
	SupplierAcmeItemRequestRepository repository;


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

		request.unbind(entity, model, "ticker", "acmeItem.title", "quantity", "buyer.userAccount.username");
	}

	@Override
	public Collection<AcmeItemRequest> findMany(final Request<AcmeItemRequest> request) {
		assert request != null;
		Collection<AcmeItemRequest> result;

		int supplierId = request.getPrincipal().getActiveRoleId();

		result = this.repository.findAcmeItemRequestsToMine(supplierId);

		return result;
	}

}
