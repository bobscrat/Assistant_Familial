package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Project;
import fr.acdo.service.ProjectService;

@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
@RestController
public class ProjectController {

	private ProjectService service;

	@Autowired
	public ProjectController(ProjectService service) {
		this.service = service;
	}

	@GetMapping("/api/projects")
	public List<Project> listProjects() {
		List<Project> list = null;
		try {
			list = service.getAllProjects();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("/api/project/{id}")
	public Project getProject(@PathVariable Long id) {
		Project project = null;
		try {
			project = service.getProjectById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	@PostMapping("/api/project")
	public Project saveProject(@RequestBody @Valid Project project) {
		Project newProject = null;
		try {
			newProject = service.saveProject(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newProject;
	}

	@PutMapping("/api/project")
	public Project updateProject(@RequestBody @Valid Project project) {
		Project newProject = null;
		try {
			newProject = service.saveProject(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newProject;
	}

	@DeleteMapping("/api/project/{id}")
	public void deleteProject(@PathVariable Long id) {
		try {
			service.deleteProject(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
