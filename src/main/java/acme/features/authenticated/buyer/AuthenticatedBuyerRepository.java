/*
 * AuthenticatedBuyerRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Buyer;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBuyerRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select b from Buyer b where b.userAccount.id = ?1")
	Buyer findOneBuyerByUserAccountId(int id);

}
