// Olga
package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Priority;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.PriorityService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

	private PriorityService service;
	private ErrorMessages errMess = new ErrorMessages();

	public PriorityController(PriorityService service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Priority> listPriorities() {
		List<Priority> list = null;
		try {
			list = service.getAllPriorities();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Priority getPriority(@PathVariable Long id) {
		Priority priority = service.getPriorityById(id);
		if (null == priority) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("La priorité demandée n'a pas été trouvée.");
		}
		return priority;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Priority savePriority(@RequestBody @Valid Priority priority, BindingResult bindingResult) {
		// création en amont
		Priority newPriority = new Priority();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"La priorité n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newPriority = service.savePriority(priority);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La priorité existe déjà", Collections.emptySet());
		}
		return newPriority;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Priority updatePriority(@RequestBody @Valid Priority priority, BindingResult bindingResult) {
		// création en amont
		Priority newPriority = new Priority();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"La priorité n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newPriority = service.savePriority(priority);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La priorité existe déjà", Collections.emptySet());
		}
		return newPriority;
	}
}
