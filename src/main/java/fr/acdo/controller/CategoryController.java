package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Category;
import fr.acdo.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
@RestController
public class CategoryController {

	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping("/api/categories")
	public List<Category> listCategories() {
		List<Category> list = null;
		try {
			list = service.getAllCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("/api/category/{id}")
	public Category getCategory(@PathVariable Long id) {
		Category category = null;
		try {
			category = service.getCategoryById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	@PostMapping("/api/category")
	public Category saveCategory(@RequestBody @Valid Category category) {
		Category newCategory = null;
		try {
			newCategory = service.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCategory;
	}

	@PutMapping("/api/category")
	public Category updateCategory(@RequestBody @Valid Category category) {
		Category newCategory = null;
		try {
			newCategory = service.saveCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCategory;
	}

	@DeleteMapping("/api/category/{id}")
	public void deleteCategory(@PathVariable Long id) {
		try {
			service.deleteCategory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
