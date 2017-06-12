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

	public List<Event> getEventsWithFilters(Optional<Long> familyId, Optional<Long> userId, Optional<Long> categoryId,
			Optional<Long> projectId) {
		List<Event> list;
		// filter by userId if it exists, else by familyId
		// when there is a filter, event.done = true by default
		if (userId.isPresent()) {
			if (categoryId.isPresent() && projectId.isPresent()) {
				list = repo.findByUserIdAndCategoryIdAndProjectIdAndDone(userId, categoryId, projectId, true);
			} else if (categoryId.isPresent()) {
				list = repo.findByUserIdAndCategoryIdAndDone(userId, categoryId, true);
			} else if (projectId.isPresent()) {
				list = repo.findByUserIdAndProjectIdAndDone(userId, projectId, true);
			} else {
				list = repo.findByUserIdAndDone(userId, true);
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

	public List<Event> getEventsSearch(Long familyId, Boolean eventDone, Boolean UserActive) {
		return repo.findByFamilyIdAndDoneAndUserActive(familyId, eventDone, UserActive);
	}

	public List<Event> getEventsPredefinedByCategory(Long categoryId) {
		// predefined event : family_id = 1
		return repo.findByFamilyIdAndCategoryId((long) 1, categoryId);
	}

	public Event getEventById(Long id) {
		return repo.findOne(id);
	}

	public Event saveEvent(Event event) {
		return repo.save(event);
	}

}
