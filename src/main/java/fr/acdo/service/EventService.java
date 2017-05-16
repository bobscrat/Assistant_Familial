package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Event;
import fr.acdo.repository.EventRepository;

@Service
public class EventService {

	private EventRepository repo;

	@Autowired
	public EventService(EventRepository repo) {
		this.repo = repo;
	}
	
	public List<Event> getAllEvents() {
		return repo.findAll();
	}
	
	public Event getEventyById(Long id) {
		return repo.findOne(id);
	}
	
	public Event saveEvent(Event event) {
		return repo.save(event);
	}
	
	public void deleteEvent(Long id) {
		repo.delete(id);
	}
}
