
package fr.acdo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Event;

/**
 * @author Olga
 */
public interface EventRepository extends JpaRepository<Event, Long> {

	// QUERY METHODS from Repository

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

	List<Event> findByUserIdAndCategoryIdAndProjectIdAndDoneOrderByDeadline(Optional<Long> memberId,
			Optional<Long> categoryId, Optional<Long> projectId, boolean b);

	List<Event> findByUserIdAndCategoryIdAndDoneOrderByDeadline(Optional<Long> memberId, Optional<Long> categoryId,
			boolean b);

	List<Event> findByUserIdAndProjectIdAndDoneOrderByDeadline(Optional<Long> memberId, Optional<Long> projectId,
			boolean b);

	List<Event> findByUserIdAndDoneOrderByDeadline(Optional<Long> memberId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndProjectIdAndDoneOrderByDeadline(Optional<Long> familyId,
			Optional<Long> categoryId, Optional<Long> projectId, boolean b);

	List<Event> findByFamilyIdAndCategoryIdAndDoneOrderByDeadline(Optional<Long> familyId, Optional<Long> categoryId,
			boolean b);

	List<Event> findByFamilyIdAndProjectIdAndDoneOrderByDeadline(Optional<Long> familyId, Optional<Long> projectId,
			boolean b);

	List<Event> findByFamilyIdAndDoneOrderByDeadline(Optional<Long> familyId, boolean b);

	List<Event> findByFamilyIdAndDoneAndUserActiveOrderByDeadline(Long familyId, Boolean eventDone, Boolean userActive);

}
