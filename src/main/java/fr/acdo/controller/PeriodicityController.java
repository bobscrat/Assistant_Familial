package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.acdo.domain.Periodicity;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.PeriodicityService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/periodicities")
public class PeriodicityController {

	private ErrorMessages errMess = new ErrorMessages();
	private PeriodicityService service;

	@Autowired
	public PeriodicityController(PeriodicityService cateService) {
		this.service = cateService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Periodicity> lisPeriodicities() {
		List<Periodicity> list = null;
		try {
			list = service.getAllPeriodicities();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Periodicity getPeriodicity(@PathVariable Long id) {
		Periodicity periodicity = service.getPeriodicityById(id);
		if (null == periodicity) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("La pédiodicité demandée n'a pas été trouvée.");
		}
		return periodicity;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Periodicity savePeriodicity(@RequestBody @Valid Periodicity periodicity, BindingResult bindingResult) {
		// création en amont
		Periodicity newPeriodicity = new Periodicity();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"La périodicité n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newPeriodicity = service.savePeriodicity(periodicity);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La périodicité existe déjà", Collections.emptySet());
		}
		return newPeriodicity;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Periodicity updatePeriodicity(@RequestBody @Valid Periodicity periodicity, BindingResult bindingResult) {
		// création en amont
		Periodicity newPeriodicity = new Periodicity();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"La périodicité n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newPeriodicity = service.savePeriodicity(periodicity);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La périodicité existe déjà", Collections.emptySet());
		}
		return newPeriodicity;
	}
}
