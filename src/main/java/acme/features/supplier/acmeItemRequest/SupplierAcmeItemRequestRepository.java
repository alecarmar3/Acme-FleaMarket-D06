
package acme.features.supplier.acmeItemRequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AcmeItemRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierAcmeItemRequestRepository extends AbstractRepository {

	@Query("select air from AcmeItemRequest air where air.id = ?1")
	AcmeItemRequest findOneById(int id);

	@Query("select air from AcmeItemRequest air where air.acmeItem.supplier.id = ?1 order by air.ticker asc")
	Collection<AcmeItemRequest> findAcmeItemRequestsToMine(int id);

}
