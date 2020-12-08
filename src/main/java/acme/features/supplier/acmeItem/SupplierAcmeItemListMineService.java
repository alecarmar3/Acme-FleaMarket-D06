
package acme.features.supplier.acmeItem;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class SupplierAcmeItemListMineService implements AbstractListService<Supplier, AcmeItem> {

	@Autowired
	SupplierAcmeItemRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItem> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AcmeItem> request, final AcmeItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "title", "category", "price", "finalMode", "isNew");
	}

	@Override
	public Collection<AcmeItem> findMany(final Request<AcmeItem> request) {
		assert request != null;
		Collection<AcmeItem> result;

		int supplierId = request.getPrincipal().getActiveRoleId();

		result = this.repository.findMyAcmeItems(supplierId);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		Date thendate = cal.getTime();

		result.stream().filter(x -> x.getFinalMode().equals(true) && x.getUpdateDate().after(thendate)).forEach(x -> x.setIsNew(true));

		return result;
	}

}
