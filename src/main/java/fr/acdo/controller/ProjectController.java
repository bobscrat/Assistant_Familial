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

import fr.acdo.domain.Project;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.ProjectService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	// instanciation des classes
	private ErrorMessages errMess = new ErrorMessages();
	private ProjectService service;

	@Autowired
	public ProjectController(ProjectService cateService) {
		this.service = cateService;
	}

	// liste des projets
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> listProjects() {
		List<Project> list = null;
		try {
			list = service.getAllProjects();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	// récupération d'un projet
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Project getProject(@PathVariable Long id) {
		Project project = service.getProjectById(id);
		if (null == project) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("Le projet demandé n'a pas été trouvé.");
		}
		return project;
	}

	// enregistrement d'un projet
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Project saveProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
		// création en amont
		Project newProject = new Project();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException("Le projet n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newProject = service.saveProject(project);
		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le projet existe déjà", Collections.emptySet());
		}
		return newProject;
	}

	// màj d'un projet
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Project updateProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
		// création en amont
		Project newProject = new Project();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException("Le projet n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newProject = service.saveProject(project);
		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le projet existe déjà", Collections.emptySet());
		}
		return newProject;
	}
}
