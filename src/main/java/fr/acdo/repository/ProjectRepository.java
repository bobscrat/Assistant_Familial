package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
