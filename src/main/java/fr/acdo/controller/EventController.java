// Olga
package fr.acdo.controller;

import java.util.List;
import java.util.Optional;

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
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.EventService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/events")
public class EventController {

	private EventService service;
	private ErrorMessages errMess = new ErrorMessages();

	public EventController(EventService service) {
		this.service = service;
	}

	@GetMapping
	public List<Event> getEvents() {
		List<Event> list = service.getAllEvents();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste d'événements n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/filters")
	public List<Event> getEventsWithFilters(@RequestParam(value = "familyId") Optional<Long> familyId,
			@RequestParam(value = "userId") Optional<Long> userId,
			@RequestParam(value = "categoryId") Optional<Long> categoryId,
			@RequestParam(value = "projectId") Optional<Long> projectId) {
		List<Event> list = service.getEventsWithFilters(familyId, userId, categoryId, projectId);
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste d'événements n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/search")
	public List<Event> getEventsSearch(@RequestParam(value = "familyId") Long familyId,
			@RequestParam(value = "eventDone") Boolean eventDone,
			@RequestParam(value = "userActive") Boolean userActive) {
		List<Event> list = service.getEventsSearch(familyId, eventDone, userActive);
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste d'événements n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/predefined")
	public List<Event> getEventsPredefined(@RequestParam(value = "categoryId") Long categoryId) {
		List<Event> list = service.getEventsPredefinedByCategory(categoryId);
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste d'événements n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Event getEvent(@PathVariable Long id) {
		Event event = service.getEventById(id);
		if (null == event) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("L'événement n'a pas été trouvé");
		}
		return event;
	}

	@PostMapping
	public Event createEvent(@RequestBody @Valid Event event) {
		Event newEvent = service.saveEvent(event);
		if (null == newEvent) {
			errMess.saveInBase(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("L'événement n'a pas été enregistré");
		}
		return newEvent;
	}

	@PutMapping
	public Event updateEvent(@RequestBody @Valid Event event) {
		Event newEvent = service.saveEvent(event);
		if (null == newEvent) {
			errMess.updateInBase(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("L'événement n'a pas été mis à jour");
		}
		return newEvent;
	}

}
