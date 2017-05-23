package fr.acdo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.acdo.domain.Person;
import fr.acdo.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository repo;

	public PersonService(PersonRepository repo) {
		this.repo = repo;
	}

	public List<Person> getAllPersons() {
		return repo.findAll();
	}

	public Person getPersonById(@PathVariable Long id) {
		return repo.findOne(id);
	}

	public Person savePerson(Person person) {
		return repo.save(person);
	}

}
