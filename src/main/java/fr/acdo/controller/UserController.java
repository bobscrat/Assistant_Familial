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

import fr.acdo.domain.User;
import fr.acdo.exception.CustomException;
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

	@GetMapping
	public List<User> listUsers() {
		List<User> listOfUsers = service.getAllUsers();
		if (null == listOfUsers) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des utilisteurs est vide");
		}
		return listOfUsers;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		User myUser = service.getUserById(id);
		if (null == myUser) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("L'utilisateur avec l'id : " + id + "n'a pas été trouvé");
		}
		return myUser;
	}

	@PostMapping
	public User addUser(@RequestBody User user) {
		User newUser = service.saveUser(user);

		if (null == newUser) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Un problème est survenu à la création");
		}
		return newUser;
	}

	@PostMapping("/log")
	public User logUser(@RequestBody User user) {

		return service.logUser(user);
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		User newUser = service.saveUser(user);

		if (null == newUser) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La mise à jour n'a pas pu être effectuée");
		}
		return newUser;
	}

}
