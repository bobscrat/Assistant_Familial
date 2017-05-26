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

import fr.acdo.domain.Family;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.FamilyService;

@RestController
@CrossOrigin(origins = "*") // à supprimer en prod
@RequestMapping("/api/families")
public class FamilyController {
	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private FamilyService service;

	@Autowired
	public FamilyController(FamilyService famService) {
		this.service = famService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Family> listFamilies() {
		List<Family> list = null;
		try {
			list = service.getAllFamilies();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Family getFamily(@PathVariable Long id) {
		Family family = service.getFamilyById(id);
		if (null == family) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("La famille demandée n'a pas été trouvée.");
		}
		return family;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Family saveFamily(@RequestBody @Valid Family family, BindingResult bindingResult) {
		// création en amont
		Family newFamily = new Family();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException("La catégorie n'a pas été enregistrée.");
		}
		try {
			newFamily = service.saveFamily(family);

		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La famille existe déjà", Collections.emptySet());
		}
		return newFamily;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Family updateFamily(@RequestBody @Valid Family family, BindingResult bindingResult) {
		// création en amont
		Family newFamily = new Family();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"La famille n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newFamily = service.saveFamily(family);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La famille existe déjà", Collections.emptySet());
		}
		return newFamily;
	}
}
