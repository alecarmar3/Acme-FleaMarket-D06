
package acme.features.administrator.figment;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Figment;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorFigmentRepository extends AbstractRepository {

	@Query("select f from Figment f where f.id = ?1")
	Figment findOneById(int id);

	@Query("select f from Figment f")
	Collection<Figment> findFigments();

}
