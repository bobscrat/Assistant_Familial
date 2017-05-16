package fr.acdo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.acdo.domain.Family;
import fr.acdo.domain.Role;
import fr.acdo.domain.User;
import fr.acdo.repository.UserDao;

@Service
public class UserService {

	private UserDao userDao;
	private Role monRole;
	private Family maFamille;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getById(@PathVariable Long id) {
		return userDao.findOne(id);
	}

	public String addUser(String firstName, LocalDate birthday) {

		Role newRole = new Role();
		newRole.setId((long) 1);
		newRole.setName("Admin Familial");

		Family newFamily = new Family();
		newFamily.setId((long) 1);
		newFamily.setName("Mouse");

		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setBirthday(birthday);
		newUser.setRoletest(newRole);
		newUser.setFamilytest(newFamily);
		userDao.save(newUser);

		return "Sauvé :)";
	}

	public User createUser(User user) {
		return userDao.save(user);
	}

}
