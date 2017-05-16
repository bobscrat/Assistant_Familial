package fr.acdo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.User;
import fr.acdo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> listUser() {
		List<User> listOfUser = null;

		try {
			listOfUser = service.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfUser;
	}

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User myUser = null;

		try {
			myUser = service.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myUser;
	}

	@PostMapping("/user/save")
	public User addUser(@RequestBody User user) {
		User newUser = null;

		try {
			newUser = service.addUser(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

}
