
package acme.features.anonymous.advertisement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Advertisement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousAdvertisementRepository extends AbstractRepository {

	@Query("select a from Advertisement a where a.id = ?1")
	Advertisement findOneById(int id);

	@Query("select a from Advertisement a where a.displayedUntil > current_date() group by a.displayedUntil")
	Collection<Advertisement> findActiveAdvertisement();

}
