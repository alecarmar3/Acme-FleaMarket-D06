
package acme.features.administrator.toolSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.ToolSheet;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorToolSheetRepository extends AbstractRepository {

	@Query("select ts from ToolSheet ts where ts.id = ?1")
	ToolSheet findOneById(int id);

	@Query("select ts from ToolSheet ts")
	Collection<ToolSheet> findToolSheets();

}
