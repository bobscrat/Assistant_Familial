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

import fr.acdo.domain.User;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> listUsers() {
		List<User> list = null;
		try {
			list = service.getAllUsers();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			System.out.println("Je dis que la BDD est DEAD");
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable Long id) {
		User user = service.getUserById(id);
		if (null == user) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("L'utilisateur demandé n'a pas été trouvé.");
		}
		return user;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		// création en amont
		User newUser = new User();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"L'utilisateur n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newUser = service.saveUser(user);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("L'utilisateur existe déjà", Collections.emptySet());
		}
		return newUser;
	}

	@PostMapping("/log")
	public User logUser(@RequestBody User user) {
		return service.logUser(user);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		// création en amont
		User newUser = new User();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"L'utilisateur n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newUser = service.saveUser(user);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("L'utilisateur existe déjà", Collections.emptySet());
		}
		return newUser;
	}
}
