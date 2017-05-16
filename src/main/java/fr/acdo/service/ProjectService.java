package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Project;
import fr.acdo.repository.ProjectRepository;

@Service
public class ProjectService {

	private ProjectRepository repoServ;

	@Autowired
	public ProjectService(ProjectRepository repoServ) {
		this.repoServ = repoServ;
	}

	public List<Project> getAllProjects() {
		return repoServ.findAll();
	}

	public Project getProjectById(Long id) {
		return repoServ.findOne(id);
	}

	public Project saveProject(Project project) {
		return repoServ.save(project);
	}

	public void deleteProject(Long id) {
		repoServ.delete(id);
	}

}