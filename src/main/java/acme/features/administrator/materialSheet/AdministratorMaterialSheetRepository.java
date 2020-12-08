
package acme.features.administrator.materialSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.MaterialSheet;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorMaterialSheetRepository extends AbstractRepository {

	@Query("select ms from MaterialSheet ms where ms.id = ?1")
	MaterialSheet findOneById(int id);

	@Query("select ms from MaterialSheet ms")
	Collection<MaterialSheet> findMaterialSheets();

}
