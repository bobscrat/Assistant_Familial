package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Contact;
import fr.acdo.repository.ContactRepository;

@Service
public class ContactService {

	private ContactRepository repo;

	@Autowired
	public ContactService(ContactRepository repo) {
		this.repo = repo;
	}

	public List<Contact> getAllContacts() {
		return repo.findAll();
	}

	public Contact getContactById(Long id) {
		return repo.findOne(id);
	}

	public Contact saveContact(Contact contact) {
		return repo.save(contact);
	}

	public void deleteContact(Long id) {
		repo.delete(id);
	}

}
