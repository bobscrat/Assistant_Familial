package fr.acdo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Category;
import fr.acdo.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository repo;

	@Autowired
	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}

	public List<Category> getAllCategories() {
		return repo.findAll();
	}

	public Category getCategoryById(Long id) {
		return repo.findOne(id);
	}

	public Category saveCategory(Category category) {
		return repo.save(category);
	}

	public void deleteCategory(Long id) {
		repo.delete(id);
	}

	public List<Category> getCategoriesWithFilters(Optional<Long> id) {
		return repo.findByFamilyId(id);
	}

}
