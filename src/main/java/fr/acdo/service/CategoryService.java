package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Category;
import fr.acdo.repository.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository repoCat;

	@Autowired
	public CategoryService(CategoryRepository repoCat) {
		this.repoCat = repoCat;
	}

	public List<Category> getAllCategories() {
		return repoCat.findAll();
	}

	public Category getCategoryById(Long id) {
		return repoCat.findOne(id);
	}

	public Category saveCategory(Category category) {
		return repoCat.save(category);
	}

	public void deleteCategory(Long id) {
		repoCat.delete(id);
	}

}
