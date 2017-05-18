package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Event;
import fr.acdo.exception.CustomException;
import fr.acdo.service.EventService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/events")
public class EventController {

	private EventService service;

	public EventController(EventService service) {
		this.service = service;
	}

	@GetMapping
	public List<Event> getEventsWithFilters(@RequestParam(value = "family") Long familyId,
			@RequestParam(value = "user") Long userId, @RequestParam(value = "category") Long categoryId,
			@RequestParam(value = "project") Long projectId) {
		List<Event> list = service.getEventsWithFilters(familyId, userId, categoryId, projectId);
		if (null == list) {
			throw new CustomException("La liste d'événements n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Event getEvent(@PathVariable Long id) {
		Event event = service.getEventyById(id);
		if (null == event) {
			throw new CustomException("L'événement avec l'id = " + id + " n'a pas été trouvé");
		}
		return event;
	}

	@PostMapping
	public Event createEvent(@RequestBody @Valid Event event) {
		Event newEvent = service.saveEvent(event);
		if (null == newEvent) {
			throw new CustomException("L'événement n'a pas été enregistré");
		}
		return newEvent;
	}

	@PutMapping
	public Event updateEvent(@RequestBody @Valid Event event) {
		Event newEvent = service.saveEvent(event);
		if (null == newEvent) {
			throw new CustomException("L'événement avec l'id = " + event.getId() + " n'a pas été mis à jour");
		}
		return newEvent;
	}

}
