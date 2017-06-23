package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {

	List<Project> findByFamilyId(Long familyId);

	List<Project> findByFamilyIdOrderByName(Long familyId);

}
