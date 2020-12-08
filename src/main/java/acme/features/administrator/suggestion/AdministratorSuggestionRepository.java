
package acme.features.administrator.suggestion;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Suggestion;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSuggestionRepository extends AbstractRepository {

	@Query("select s from Suggestion s where s.id = ?1")
	Suggestion findOneById(int id);

	@Query("select s from Suggestion s")
	Collection<Suggestion> findSuggestions();

}
