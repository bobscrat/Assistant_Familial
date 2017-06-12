package fr.acdo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Project;
import fr.acdo.repository.ProjectRepository;

@Service
public class ProjectService {

	private ProjectRepository repo;

	@Autowired
	public ProjectService(ProjectRepository repo) {
		this.repo = repo;
	}

	public List<Project> getAllProjects() {
		return repo.findAll();
	}

	public Project getProjectById(Long id) {
		return repo.findOne(id);
	}

	public Project saveProject(Project project) {
		return repo.save(project);
	}

	public void deleteProject(Long id) {
		repo.delete(id);
	}

	public List<Project> getProjectsWithFilters(Optional<Long> id) {
		return repo.findByFamilyId(id);
	}

}