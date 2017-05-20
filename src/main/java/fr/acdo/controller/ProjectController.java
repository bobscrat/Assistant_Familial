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

import fr.acdo.domain.Project;
import fr.acdo.exception.CustomException;
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
	@GetMapping
	public List<Project> listProjects() {
		List<Project> list = service.getAllProjects();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des projets n'a pas été trouvée");
		}
		return list;
	}

	// récupération d'un projet
	@GetMapping("/{id}")
	public Project getProject(@PathVariable Long id) {

		Project project = service.getProjectById(id);
		if (null == project) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le projet avec l'id = " + id + " n'a pas été trouvé");
		}
		return project;
	}

	// enregistrement d'un projet
	@PostMapping
	public Project saveProject(@RequestBody @Valid Project project) {
		Project newProject = service.saveProject(project);
		if (null == project) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le projet n'a pas été enregistré");
		}
		return newProject;
	}

	// màj d'un projet
	@PutMapping
	public Project updateProject(@RequestBody @Valid Project project) {
		Project newProject = service.saveProject(project);
		if (null == project) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("Le projet n'a pas été mis à jour.");
		}
		return newProject;
	}

}
