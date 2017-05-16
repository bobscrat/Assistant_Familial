package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Contact;
import fr.acdo.service.ContactService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
public class ContactController {

	private ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@GetMapping("api/contact")
	public List<Contact> listContact() {
		List<Contact> myList = null;
		try {
			myList = contactService.getAllContacts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	@GetMapping("api/contact/{id}")
	public Contact getAContact(@PathVariable Long id) {
		Contact aContact = contactService.getContactById(id);
		return aContact;
	}

	@PostMapping("/api/contact")
	public Contact saveContact(@RequestBody @Valid Contact contact) {
		Contact newContact = null;
		try {
			newContact = contactService.addNewContact(newContact);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newContact;
	}

	@DeleteMapping("/api/contact/{id}")
	public void deleteContact(@PathVariable Long id) {
		try {
			contactService.deleteContact(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
