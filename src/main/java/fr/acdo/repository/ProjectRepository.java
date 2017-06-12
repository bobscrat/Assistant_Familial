package fr.acdo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByFamilyId(Optional<Long> id);

}
