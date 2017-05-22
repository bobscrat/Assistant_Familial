// Olga
package fr.acdo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	// QUERY METHODS de la classe Repository

	List<Event> findByUserIdAndCategoryIdAndProjectIdAndDone(Optional<Long> userId, Optional<Long> categoryId,
			Optional<Long> projectId, boolean b);

	List<Event> findByUserIdAndCategoryIdAndDone(Optional<Long> userId, Optional<Long> categoryId, boolean b);

	List<Event> findByUserIdAndProjectIdAndDone(Optional<Long> userId, Optional<Long> projectId, boolean b);

	List<Event> findByUserIdAndDone(Optional<Long> userId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndProjectIdAndDone(Optional<Long> familyId, Optional<Long> categoryId,
			Optional<Long> projectId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndDone(Optional<Long> familyId, Optional<Long> categoryId, boolean b);

	List<Event> findByFamilyIdAndProjectIdAndDone(Optional<Long> familyId, Optional<Long> projectId, boolean b);

	List<Event> findByFamilyIdAndDone(Optional<Long> familyId, boolean b);

	List<Event> findByFamilyIdAndCategoryId(long l, Long categoryId);

	List<Event> findByFamilyIdAndDoneAndUserActive(Long familyId, Boolean eventDone, Boolean userActive);

}
