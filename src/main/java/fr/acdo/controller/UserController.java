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
import fr.acdo.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> listUser() {
		List<User> listOfUser = null;

		try {
			listOfUser = service.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfUser;
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		User myUser = null;

		try {
			myUser = service.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myUser;
	}

	@PostMapping
	public User addUser(@RequestBody User user) {
		User newUser = null;

		try {
			newUser = service.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		User newUser = null;

		try {
			newUser = service.addUser(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

}
