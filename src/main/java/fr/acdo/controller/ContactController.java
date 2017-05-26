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

import fr.acdo.domain.Contact;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.ContactService;

@RestController
@CrossOrigin(origins = "*") // à supprimer en prod
@RequestMapping("/api/contacts")
public class ContactController {
	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private ContactService service;

	@Autowired
	public ContactController(ContactService contactService) {
		this.service = contactService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Contact> listContact() {
		List<Contact> list = null;
		try {
			list = service.getAllContacts();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Contact getContact(@PathVariable Long id) {
		Contact contact = service.getContactById(id);
		if (null == contact) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("Le contact n'a pas été trouvé.");
		}
		return contact;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Contact saveContact(@RequestBody @Valid Contact contact, BindingResult bindingResult) {
		// création en amont
		Contact newContact = new Contact();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException("Le contact n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newContact = service.saveContact(contact);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le contact existe déjà", Collections.emptySet());
		}
		return newContact;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Contact updateContact(@RequestBody @Valid Contact contact, BindingResult bindingResult) {
		// création en amont
		Contact newContact = new Contact();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException("Le contact n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newContact = service.saveContact(contact);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le contact existe déjà", Collections.emptySet());
		}
		return newContact;
	}
}
