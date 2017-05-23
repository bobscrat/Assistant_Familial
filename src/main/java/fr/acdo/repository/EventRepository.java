// Olga
package fr.acdo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	// QUERY METHODS de la classe Repository

	List<Event> findByPersonIdAndCategoryIdAndProjectIdAndDone(Optional<Long> personId, Optional<Long> categoryId,
			Optional<Long> projectId, boolean b);

	List<Event> findByPersonIdAndCategoryIdAndDone(Optional<Long> personId, Optional<Long> categoryId, boolean b);

	List<Event> findByPersonIdAndProjectIdAndDone(Optional<Long> personId, Optional<Long> projectId, boolean b);

	List<Event> findByPersonIdAndDone(Optional<Long> userId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndProjectIdAndDone(Optional<Long> familyId, Optional<Long> categoryId,
			Optional<Long> projectId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndDone(Optional<Long> familyId, Optional<Long> categoryId, boolean b);

	List<Event> findByFamilyIdAndProjectIdAndDone(Optional<Long> familyId, Optional<Long> projectId, boolean b);

	List<Event> findByFamilyIdAndDone(Optional<Long> familyId, boolean b);

	List<Event> findByFamilyIdAndCategoryId(long l, Long categoryId);

	List<Event> findByFamilyIdAndDoneAndPersonActive(Long familyId, Boolean eventDone, Boolean personActive);

}
