
package acme.features.authenticated.acmeItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AcmeItem;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAcmeItemRepository extends AbstractRepository {

	@Query("select ai from AcmeItem ai where ai.id = ?1")
	AcmeItem findOneById(int id);

	@Query("select ai from AcmeItem ai where ai.finalMode = true")
	Collection<AcmeItem> findAllAcmeItem();

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

}
