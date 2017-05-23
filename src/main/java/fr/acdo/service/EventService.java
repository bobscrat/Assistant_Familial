// Olga
package fr.acdo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.acdo.domain.Event;
import fr.acdo.repository.EventRepository;

@Service
public class EventService {

	private EventRepository repo;

	public EventService(EventRepository repo) {
		this.repo = repo;
	}

	public List<Event> getAllEvents() {
		return repo.findAll();
	}

	public List<Event> getEventsWithFilters(Optional<Long> familyId, Optional<Long> personId, Optional<Long> categoryId,
			Optional<Long> projectId) {
		List<Event> list;
		// on filtre par personId s'il est renseigné, sinon par familyId
		// avec les filtres, event.done = true
		if (personId.isPresent()) {
			if (categoryId.isPresent() && projectId.isPresent()) {
				list = repo.findByPersonIdAndCategoryIdAndProjectIdAndDone(personId, categoryId, projectId, true);
			} else if (categoryId.isPresent()) {
				list = repo.findByPersonIdAndCategoryIdAndDone(personId, categoryId, true);
			} else if (projectId.isPresent()) {
				list = repo.findByPersonIdAndProjectIdAndDone(personId, projectId, true);
			} else {
				list = repo.findByPersonIdAndDone(personId, true);
			}
		} else {
			if (categoryId.isPresent() && projectId.isPresent()) {
				list = repo.findByFamilyIdAndCategoryIdAndProjectIdAndDone(familyId, categoryId, projectId, true);
			} else if (categoryId.isPresent()) {
				list = repo.findByFamilyIdAndCategoryIdAndDone(familyId, categoryId, true);
			} else if (projectId.isPresent()) {
				list = repo.findByFamilyIdAndProjectIdAndDone(familyId, projectId, true);
			} else {
				list = repo.findByFamilyIdAndDone(familyId, true);
			}
		}
		return list;
	}

	public List<Event> getEventsSearch(Long familyId, Boolean eventDone, Boolean personActive) {
		return repo.findByFamilyIdAndDoneAndPersonActive(familyId, eventDone, personActive);
	}

	public List<Event> getEventsPredefinedByCategory(Long categoryId) {
		// event prédéfini : family_id = 1
		return repo.findByFamilyIdAndCategoryId((long) 1, categoryId);
	}

	public Event getEventById(Long id) {
		return repo.findOne(id);
	}

	public Event saveEvent(Event event) {
		return repo.save(event);
	}

}
