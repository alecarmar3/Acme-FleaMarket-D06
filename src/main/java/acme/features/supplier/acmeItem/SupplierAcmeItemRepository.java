
package acme.features.supplier.acmeItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.SpecificationSheet;
import acme.entities.roles.Supplier;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierAcmeItemRepository extends AbstractRepository {

	@Query("select ai from AcmeItem ai where ai.id = ?1")
	AcmeItem findOneById(int id);

	@Query("select ai from AcmeItem ai where ai.supplier.id = ?1")
	Collection<AcmeItem> findMyAcmeItems(int id);

	@Query("select s from Supplier s where s.id = ?1")
	Supplier findSupplierById(int authId);

	@Query("select ai.ticker from AcmeItem ai")
	Collection<String> findTickersInUse();

	@Query("select ai.finalMode from AcmeItem ai where ai.id = ?1")
	Boolean findFinalMode(int id);

	@Query("select c from Configuration c")
	Collection<Configuration> findManyConfiguration();

	@Query("select ai from AcmeItem ai where ai.ticker = ?1")
	AcmeItem findAcmeItemByTicker(String ticker);

	@Query("select ss from SpecificationSheet ss where ss.acmeItem.id = ?1")
	Collection<SpecificationSheet> findSpecificationSheetsByAcmeItem(int id);

	@Query("select air.acmeItem from AcmeItemRequest air where air.acmeItem.id = ?1")
	Collection<AcmeItem> findAcmeItemRequestsForThisAcmeItem(int id);

}
