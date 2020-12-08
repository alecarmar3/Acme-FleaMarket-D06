
package acme.features.supplier.specificationSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.AcmeItem;
import acme.entities.Configuration;
import acme.entities.SpecificationSheet;
import acme.framework.entities.UserRole;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierSpecificationSheetRepository extends AbstractRepository {

	@Query("select ss from SpecificationSheet ss where ss.id = ?1")
	SpecificationSheet findOneById(int id);

	@Query("select ai from AcmeItem ai where ai.id = ?1")
	AcmeItem findAcmeItemById(int id);

	@Query("select ss from SpecificationSheet ss where ss.acmeItem.id = ?1")
	Collection<SpecificationSheet> findSpecificationSheetsByAcmeItem(int id);

	@Query("select ss.indexer from SpecificationSheet ss where ss.acmeItem.id = ?1")
	Collection<Integer> findSpecificationSheetsIndexersByAcmeItem(int id);

	@Query("select ur from UserRole ur where ur.userAccount.id = ?1")
	Collection<UserRole> findUserAccountRoles(int id);

	@Query("select c from Configuration c")
	Collection<Configuration> findManyConfiguration();

}
