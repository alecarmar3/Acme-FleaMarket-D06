
package acme.features.authenticated.materialSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.MaterialSheet;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMaterialSheetRepository extends AbstractRepository {

	@Query("select ms from MaterialSheet ms where ms.id = ?1")
	MaterialSheet findOneById(int id);

	@Query("select ms from MaterialSheet ms group by ms.stars")
	Collection<MaterialSheet> findAllMaterialSheet();

}
