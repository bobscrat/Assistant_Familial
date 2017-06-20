package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Project;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.ProjectService;

@CrossOrigin(origins = "*") // to be deleted in prod
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	// instantiation of the classes
	private ErrorMessages errMess = new ErrorMessages();
	private ProjectService service;

	@Autowired
	public ProjectController(ProjectService cateService) {
		this.service = cateService;
	}

	// to get all projects
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> listProjects() {
		List<Project> list = null;
		try {
			list = service.getAllProjects();
		} catch (CannotCreateTransactionException e) { // no database access
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	// to get projects when applying filters
	@GetMapping("/filters")
	public List<Project> getProjectsWithFilters(@RequestParam(value = "familyId") Long familyId,
			@RequestParam(value = "getInactive") Optional<Boolean> getInactive) {
		List<Project> list = null;
		try {
			list = service.getProjectsWithFilters(familyId, getInactive);
		} catch (CannotCreateTransactionException e) { // no database access
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	// to get a project
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

	// to save a project
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Project saveProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
		Project newProject = new Project();
		// to manage @Valid exceptions
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException("Le projet n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newProject = service.saveProject(project);
		} catch (DataIntegrityViolationException e) {// integrity violation
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le projet existe déjà", Collections.emptySet());
		}
		return newProject;

	}

	// to update a project
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Project updateProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
		Project newProject = new Project();
		// to manage @Valid exceptions
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException("Le projet n'a pas été enregistré car au moins un champ est invalide.");
		}
		try {
			newProject = service.saveProject(project);
		} catch (DataIntegrityViolationException e) { // integrity violation
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("Le projet existe déjà", Collections.emptySet());
		}
		return newProject;
	}
}
