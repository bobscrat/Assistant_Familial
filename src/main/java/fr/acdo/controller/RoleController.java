package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Role;
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.RoleService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*") // à supprimer en prod
public class RoleController {

	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private RoleService service;

	// Constructeur
	@Autowired
	public RoleController(RoleService roleService) {
		this.service = roleService;
	}

	@GetMapping
	public List<Role> listRoles() {
		List<Role> myList = service.getAllRole();
		if (null == myList) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des roles n'a pas été trouvée");
		}
		return myList;
	}

	@GetMapping("/{id}")
	public Role getRole(@PathVariable Long id) {
		Role role = service.getRoleById(id);
		if (null == role) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des roles n'a pas été trouvée");
		}
		return role;
	}

	@PostMapping
	public Role addRole(@RequestBody @Valid Role role) {
		Role newRole = service.saveRole(role);
		if (null == newRole) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Une erreur est survenue à l'enregistrement du role");
		}
		return newRole;
	}

	@PutMapping
	public Role updateRole(@RequestBody @Valid Role role) {
		Role newRole = service.saveRole(role);
		if (null == newRole) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le role n'a pas été mis à jour");
		}
		return newRole;
	}

}
