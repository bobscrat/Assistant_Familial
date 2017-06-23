package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByFamilyId(Long familyId);

	List<Category> findByFamilyIdOrFamilyId(Long familyId, long l);

	List<Category> findByFamilyIdOrderByName(Long familyId);

	List<Category> findByFamilyIdOrFamilyIdOrderByName(Long familyId, long l);

}
