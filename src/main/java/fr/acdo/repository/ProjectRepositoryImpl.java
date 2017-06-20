package fr.acdo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.acdo.domain.Project;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Project> findByFamilyIdAndHasOnlyActiveEvent(Long familyId) {
		Query query = entityManager.createNativeQuery(
				"SELECT p.* FROM project p JOIN event e ON e.project_id = p.id WHERE e.done = 0 AND e.family_id = ?",
				Project.class).setParameter(1, familyId);
		return query.getResultList();
	};

}
