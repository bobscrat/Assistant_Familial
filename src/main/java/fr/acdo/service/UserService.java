package fr.acdo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.acdo.domain.User;
import fr.acdo.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public User logUser(User user) {
		return repo.findByEmailAndPassword(user.getEmail(), user.getPassword());
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public User getUserById(@PathVariable Long id) {
		return repo.findOne(id);
	}

	public User saveUser(User user) {
		return repo.save(user);
	}

}
