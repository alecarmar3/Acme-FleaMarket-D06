
package acme.features.buyer.acmeItemRequest;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AcmeItem;
import acme.entities.AcmeItemRequest;
import acme.entities.AcmeItemRequestStatus;
import acme.entities.roles.Buyer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BuyerAcmeItemRequestCreateService implements AbstractCreateService<Buyer, AcmeItemRequest> {

	@Autowired
	BuyerAcmeItemRequestRepository repository;


	@Override
	public boolean authorise(final Request<AcmeItemRequest> request) {
		assert request != null;

		int id = request.getPrincipal().getAccountId();
		AcmeItem acmeItem = this.repository.findAcmeItemById(request.getModel().getInteger("AcmeItemId"));

		Collection<String> roles = this.repository.findUserAccountRoles(id).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean isFinalMode = acmeItem.getFinalMode();
		Boolean isBuyer = roles.contains("Buyer");

		return isFinalMode && isBuyer;

	}
	@Override
	public void bind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(now);

		if (!request.isMethod(HttpMethod.GET)) {
			AcmeItem acmeItem = entity.getAcmeItem();
			request.getModel().setAttribute("acmeItem", acmeItem);
		}

		request.bind(entity, errors, "creationDate", "status", "buyer");
	}
	@Override
	public void unbind(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "quantity", "notes", "acmeItem");
	}
	@Override
	public AcmeItemRequest instantiate(final Request<AcmeItemRequest> request) {

		AcmeItemRequest result = new AcmeItemRequest();
		Principal principal = request.getPrincipal();

		AcmeItem acmeItem = this.repository.findAcmeItemById(request.getModel().getInteger("AcmeItemId"));
		Buyer buyer = this.repository.findBuyerById(principal.getActiveRoleId());

		result.setStatus(AcmeItemRequestStatus.PENDING);
		result.setBuyer(buyer);
		result.setAcmeItem(acmeItem);

		return result;

	}
	@Override
	public void validate(final Request<AcmeItemRequest> request, final AcmeItemRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		AcmeItem acmeItem = this.repository.findAcmeItemById(request.getModel().getInteger("AcmeItemId"));

		String categoryOfThisAcmeItem = acmeItem.getCategory();

		Collection<String> tickersInUse = this.repository.findTickersInUse();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		if (!errors.hasErrors("ticker")) {

			Boolean categoryLetters = false;

			if (categoryOfThisAcmeItem.length() >= 3) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().subSequence(0, 3));
			} else if (categoryOfThisAcmeItem.length() == 2) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().concat("X"));
			} else if (categoryOfThisAcmeItem.length() == 1) {
				categoryLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(categoryOfThisAcmeItem.toUpperCase().concat("XX"));
			}

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			Boolean tickerAvailable = !tickersInUse.contains(request.getModel().getAttribute("ticker"));

			errors.state(request, categoryLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			errors.state(request, tickerAvailable, "ticker", "default.error.already-in-use", entity.getTicker());
		}

	}
	@Override
	public void create(final Request<AcmeItemRequest> request, final AcmeItemRequest entity) {
		this.repository.save(entity);
	}

}
