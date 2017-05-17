package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	// QUERY METHODS de la classe Repository

	List<Event> findByUserId(Long id);

	List<Event> findByCategoryId(Long id);

	List<Event> findByProjectId(Long id);

	List<Event> findByUserIdAndCategoryId(Long idUser, Long idCategory);

	List<Event> findByUserIdAndProjectId(Long idUser, Long idProject);

	List<Event> findByUserIdAndCategoryIdAndProjectId(Long idUser, Long idCategory, Long idProject);

	List<Event> findByCategoryIdAndProjectId(Long idCategory, Long idProject);

}
