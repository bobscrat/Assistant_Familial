package fr.acdo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Person;
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.PersonService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/persons")
public class PersonController {

	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();

	private PersonService service;

	public PersonController(PersonService service) {
		this.service = service;
	}

	@GetMapping
	public List<Person> listPersons() {
		List<Person> listOfPersons = service.getAllPersons();
		if (null == listOfPersons) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des personnes est vide");
		}
		return listOfPersons;
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable Long id) {
		Person myPerson = service.getPersonById(id);
		if (null == myPerson) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La personne avec l'id : " + id + "n'a pas été trouvé");
		}
		return myPerson;
	}

	@PostMapping
	public Person addPerson(@RequestBody Person person) {
		Person newPerson = service.savePerson(person);

		if (null == newPerson) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Un problème est survenu à la création");
		}
		return newPerson;
	}

	@PutMapping
	public Person updatePerson(@RequestBody Person person) {
		Person newPerson = service.savePerson(person);

		if (null == newPerson) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La mise à jour n'a pas pu être effectuée");
		}
		return newPerson;
	}

}
