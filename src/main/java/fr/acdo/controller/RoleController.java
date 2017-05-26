package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.acdo.domain.Role;
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Role> listRoles() {
		List<Role> list = null;
		try {
			list = service.getAllRoles();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Role getRole(@PathVariable Long id) {
		Role role = service.getRoleById(id);
		if (null == role) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("Le rôle demandé n'a pas été trouvé.");
		}
		return role;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Role saveRole(@RequestBody @Valid Role role, BindingResult bindingResult) {
		// création en amont
		Role newRole = new Role();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException("Le rôle n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newRole = service.saveRole(role);

		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le rôle existe déjà", Collections.emptySet());
		}
		return newRole;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Role updateRole(@RequestBody @Valid Role role, BindingResult bindingResult) {
		// création en amont
		Role newRole = new Role();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException("Le rôle n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newRole = service.saveRole(role);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le rôle existe déjà", Collections.emptySet());
		}
		return newRole;
	}
}
