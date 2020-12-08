
package acme.features.authenticated.specificationSheet;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SpecificationSheet;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedSpecificationSheetListService implements AbstractListService<Authenticated, SpecificationSheet> {

	@Autowired
	AuthenticatedSpecificationSheetRepository repository;


	@Override
	public boolean authorise(final Request<SpecificationSheet> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "indexer", "sheetTitle", "acmeItem.title");
	}

	@Override
	public Collection<SpecificationSheet> findMany(final Request<SpecificationSheet> request) {
		assert request != null;
		Collection<SpecificationSheet> result;

		int id = request.getModel().getInteger("AcmeItemId");

		result = this.repository.findSpecificationSheetsByAcmeItem(id);

		return result;
	}

}
