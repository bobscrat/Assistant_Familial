package fr.acdo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Role;
import fr.acdo.service.RoleService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // à supprimer en prod
public class RoleController {

	// Je fais appel au Service
	private RoleService roleService;

	// Constructeur
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("/role")
	public List<Role> listRole() {
		List<Role> myList = null;
		try {
			myList = roleService.getAllRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	@GetMapping("/role/{id}")
	public Role getId(@PathVariable Long id) {
		Role role = null;
		try {
			role = roleService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@PostMapping("/role")
	public Role addRole(@RequestBody Role role) {
		return roleService.saveRole(role);
	}

}
