package fr.acdo.service;

import java.util.List;
import java.util.Optional;

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
		user.setActive(true);
		user.setImage("12-32x32.png");
		return repo.save(user);
	}

	public User updateUser(User user) {
		return repo.save(user);
	}

	public List<User> getUsersWithFilters(Long familyId, Optional<Boolean> isActive) {
		List<User> list;
		if (isActive.isPresent()) {
			list = repo.findByFamilyIdAndActiveOrderByFirstName(familyId, isActive.get());
		} else {
			list = repo.findByFamilyIdOrderByFirstName(familyId);
		}
		return list;
	}

}
