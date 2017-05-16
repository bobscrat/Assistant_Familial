package fr.acdo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.acdo.domain.User;
import fr.acdo.repository.UserDao;

@Service
public class UserService {

	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getById(@PathVariable Long id) {
		return userDao.findOne(id);
	}

	public User addUser(User user) {
		return userDao.save(user);
	}

}
