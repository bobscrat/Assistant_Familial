package fr.acdo.repository;

import java.util.List;

import fr.acdo.domain.Project;

public interface ProjectRepositoryCustom {

	List<Project> findByFamilyIdAndHasOnlyActiveEvent(Long familyId);
}
