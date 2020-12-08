
package acme.features.buyer.acmeItemRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AcmeItem;
import acme.entities.AcmeItemRequest;
import acme.entities.roles.Buyer;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BuyerAcmeItemRequestRepository extends AbstractRepository {

	@Query("select air from AcmeItemRequest air where air.id = ?1")
	AcmeItemRequest findOneById(int id);

	@Query("select air from AcmeItemRequest air where air.buyer.id = ?1")
	Collection<AcmeItemRequest> findMyAcmeItemRequests(int id);

	@Query("select ai from AcmeItem ai where ai.id = ?1")
	AcmeItem findAcmeItemById(int id);

	@Query("select b from Buyer b where b.id=?1")
	Buyer findBuyerById(int id);

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

	@Query("select air.ticker from AcmeItemRequest air")
	Collection<String> findTickersInUse();

}
