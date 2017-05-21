package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Contact;
import fr.acdo.exception.CustomException;
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

	@GetMapping
	public List<Contact> listContact() {
		List<Contact> list = service.getAllContacts();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des contacts n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Contact getContact(@PathVariable Long id) {
		Contact contact = service.getContactById(id);
		if (null == contact) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le contact avec l'id = " + id + " n'a pas été trouvé");
		}
		return contact;
	}

	@PostMapping
	public Contact saveContact(@RequestBody @Valid Contact contact) {
		Contact newContact = service.saveContact(contact);
		if (null == contact) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le contact n'a pas été enregistré");
		}
		return newContact;
	}

	@PutMapping
	public Contact updateContact(@RequestBody @Valid Contact contact) {
		Contact newContact = service.saveContact(contact);
		if (null == contact) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le contact n'a pas été mis à jour");
		}
		return newContact;
	}

}
