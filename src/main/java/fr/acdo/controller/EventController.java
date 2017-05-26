// Olga
package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Event;
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Event> listEvents() {
		List<Event> list = null;
		try {
			list = service.getAllEvents();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping("/filters")
	public List<Event> getEventsWithFilters(@RequestParam(value = "familyId") Optional<Long> familyId,
			@RequestParam(value = "userId") Optional<Long> userId,
			@RequestParam(value = "categoryId") Optional<Long> categoryId,
			@RequestParam(value = "projectId") Optional<Long> projectId) {
		List<Event> list = null;
		try {
			list = service.getEventsWithFilters(familyId, userId, categoryId, projectId);
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping("/search")
	public List<Event> getEventsSearch(@RequestParam(value = "familyId") Long familyId,
			@RequestParam(value = "eventDone") Boolean eventDone,
			@RequestParam(value = "userActive") Boolean userActive) {
		List<Event> list = null;
		try {
			list = service.getEventsSearch(familyId, eventDone, userActive);
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
			// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping("/predefined")
	public List<Event> getEventsPredefined(@RequestParam(value = "categoryId") Long categoryId) {
		List<Event> list = null;
		try {
			list = service.getEventsPredefinedByCategory(categoryId);
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
			// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Event getEvent(@PathVariable Long id) {

		Event event = service.getEventById(id);
		if (null == event) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("L'événement demandé n'a pas été trouvé.");
		}
		return event;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Event saveEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
		// création en amont
		Event newEvent = new Event();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"L'évenement n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newEvent = service.saveEvent(event);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("L'événement existe déjà", Collections.emptySet());
		}
		return newEvent;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Event updateEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
		// création en amont
		Event newEvent = new Event();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"L'événement n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newEvent = service.saveEvent(event);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("L'événement existe déjà", Collections.emptySet());
		}
		return newEvent;
	}

}
