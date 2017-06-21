package fr.acdo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.acdo.domain.Event;
import fr.acdo.repository.EventRepository;

/**
 * @author Olga
 */
@Service
public class EventService {

	private EventRepository repo;

	public EventService(EventRepository repo) {
		this.repo = repo;
	}

	/**
	 * Get all Events
	 * 
	 * @return a list of Events
	 */
	public List<Event> getAllEvents() {
		return repo.findAll();
	}

	/**
	 * Get a list of Events when applying filters
	 * 
	 * @param familyId
	 * @param memberId
	 * @param categoryId
	 * @param projectId
	 * @return a list of Events
	 */
	public List<Event> getEventsWithFilters(Optional<Long> familyId, Optional<Long> memberId, Optional<Long> categoryId,
			Optional<Long> projectId) {
		List<Event> list;

		// filter by memberId if it exists, else by familyId
		// when there is a filter, event.done = false by default (=not done)
		if (memberId.isPresent()) {
			if (categoryId.isPresent() && projectId.isPresent()) {
				list = repo.findByUserIdAndCategoryIdAndProjectIdAndDone(memberId, categoryId, projectId, false);
			} else if (categoryId.isPresent()) {
				list = repo.findByUserIdAndCategoryIdAndDone(memberId, categoryId, false);
			} else if (projectId.isPresent()) {
				list = repo.findByUserIdAndProjectIdAndDone(memberId, projectId, false);
			} else {
				list = repo.findByUserIdAndDone(memberId, false);
			}
		} else {
			if (categoryId.isPresent() && projectId.isPresent()) {
				list = repo.findByFamilyIdAndCategoryIdAndProjectIdAndDone(familyId, categoryId, projectId, false);
			} else if (categoryId.isPresent()) {
				list = repo.findByFamilyIdAndCategoryIdAndDone(familyId, categoryId, false);
			} else if (projectId.isPresent()) {
				list = repo.findByFamilyIdAndProjectIdAndDone(familyId, projectId, false);
			} else {
				list = repo.findByFamilyIdAndDone(familyId, false);
			}
		}
		return list;
	}

	/**
	 * Get Events as a search result
	 * 
	 * @param familyId
	 * @param eventDone
	 * @param UserActive
	 * @return a list of Events
	 */
	public List<Event> getEventsSearch(Long familyId, Boolean eventDone, Boolean UserActive) {
		return repo.findByFamilyIdAndDoneAndUserActive(familyId, eventDone, UserActive);
	}

	/**
	 * Get only predefined Events, by category
	 * 
	 * @param categoryId
	 * @return a list of Events
	 */
	public List<Event> getEventsPredefinedByCategory(Long categoryId) {
		// predefined event : family_id = 1
		return repo.findByFamilyIdAndCategoryId((long) 1, categoryId);
	}

	/**
	 * Get an Event by id
	 * 
	 * @param id
	 * @return an Event
	 */
	public Event getEventById(Long id) {
		return repo.findOne(id);
	}

	/**
	 * Save an Event in the database
	 * 
	 * @param event
	 * @return an Event
	 */
	public Event saveEvent(Event event) {
		return repo.save(event);
	}

}
