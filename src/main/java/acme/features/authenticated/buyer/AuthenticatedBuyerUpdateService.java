/*
 * AuthenticatedBuyerUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.buyer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Buyer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedBuyerUpdateService implements AbstractUpdateService<Authenticated, Buyer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBuyerRepository repository;


	// AbstractUpdateService<Authenticated, Buyer> interface -----------------

	@Override
	public boolean authorise(final Request<Buyer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("expirationDate")) {

			String[] shortExpDate;
			Integer year, month;
			shortExpDate = entity.getExpirationDate().split("/");
			month = Integer.parseInt(shortExpDate[0]);
			year = Integer.parseInt(shortExpDate[1]);

			LocalDate now;
			now = LocalDate.now();
			boolean validYear = year >= now.getYear() - 2000;
			boolean validMonth = month >= now.getMonthValue();
			boolean monthInRange = month >= 1 && month <= 12;

			if (validYear && year == now.getYear() - 2000) {
				errors.state(request, validMonth, "expirationDate", "authenticated.buyer.error.timed-out-credit-card-month");
				errors.state(request, monthInRange, "expirationDate", "authenticated.buyer.error.inexistent-month");
			} else {
				errors.state(request, validYear, "expirationDate", "authenticated.buyer.error.timed-out-credit-card-year");
				errors.state(request, monthInRange, "expirationDate", "authenticated.buyer.error.inexistent-month");
			}

		}
	}

	@Override
	public void bind(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Buyer> request, final Buyer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "email", "phoneNumber", "deliveryAddress", "creditCardNumber", "holderName", "brand", "expirationDate", "cvv");
	}

	@Override
	public Buyer findOne(final Request<Buyer> request) {
		assert request != null;

		Buyer result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneBuyerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Buyer> request, final Buyer entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Buyer> request, final Response<Buyer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
