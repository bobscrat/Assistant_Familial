package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Contact;
import fr.acdo.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository repoContact;

	@Autowired
	public ContactService(ContactRepository repoContact) {
		this.repoContact = repoContact;
	}

	public List<Contact> getAllContacts() {
		return repoContact.findAll();
	}

	public Contact getContactById(Long id) {
		return repoContact.findOne(id);
	}

	public Contact addNewContact(Contact contact) {
		return repoContact.save(contact);
	}

	public void deleteContact(Long id) {
		repoContact.delete(id);
	}

}
