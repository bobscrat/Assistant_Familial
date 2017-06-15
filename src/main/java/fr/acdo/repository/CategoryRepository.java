package fr.acdo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByFamilyId(Optional<Long> id);

}
