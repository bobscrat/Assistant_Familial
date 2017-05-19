package fr.acdo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.acdo.domain.Event;
import fr.acdo.repository.EventRepository;

@Service
public class EventService {

	private EventRepository repo;

	public EventService(EventRepository repo) {
		this.repo = repo;
	}

	public List<Event> getEventsWithFilters(Long familyId, Long userId, Long categoryId, Long projectId) {
		// à compléter avec les filtres
		return repo.findAll();
	}

	public List<Event> getAllEvents() {
		return repo.findAll();
	}

	public Event getEventById(Long id) {
		return repo.findOne(id);
	}

	public Event saveEvent(Event event) {
		return repo.save(event);
	}

	// QUERY METHODS de la classe Repository

	public List<Event> getEventsByUserId(Long id) {
		return repo.findByUserId(id);
	}

	public List<Event> getEventsByCategoryId(Long id) {
		return repo.findByCategoryId(id);
	}

	public List<Event> getEventsByProjectId(Long id) {
		return repo.findByProjectId(id);
	}

	public List<Event> getEventsByUserIdAndCategoryId(Long idUser, Long idCategory) {
		return repo.findByUserIdAndCategoryId(idUser, idCategory);
	}

	public List<Event> getEventsByUserIdAndProjectId(Long idUser, Long idProject) {
		return repo.findByUserIdAndProjectId(idUser, idProject);
	}

	public List<Event> getEventsByUserIdAndCategoryIdAndProjectId(Long idUser, Long idCategory, Long idProject) {
		return repo.findByUserIdAndCategoryIdAndProjectId(idUser, idCategory, idProject);
	}

	public List<Event> getEventsByCategoryIdAndProjectId(Long idCategory, Long idProject) {
		return repo.findByCategoryIdAndProjectId(idCategory, idProject);
	}

}
