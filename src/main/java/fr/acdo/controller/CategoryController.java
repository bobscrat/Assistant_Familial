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
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.CategoryService;

@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
@RestController
public class CategoryController {

	ErrorMessages errMess = new ErrorMessages();

	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping("/api/categories")
	public List<Category> listCategories() {
		List<Category> list = service.getAllCategories();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des catégories n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/api/category/{id}")
	public Category getCategory(@PathVariable Long id) {

		Category category = service.getCategoryById(id);
		if (null == category) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La catégory avec l'id = " + id + " n'a pas été trouvée");
		}

		return category;
	}

	@PostMapping("/api/category")
	public Category saveCategory(@RequestBody @Valid Category category) {
		Category newCategory = null;
		try {
			newCategory = service.saveCategory(category);
			errMess.saveInBase(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
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
			errMess.updateInBase(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newCategory;
	}

	@DeleteMapping("/api/category/{id}")
	public void deleteCategory(@PathVariable Long id) {
		try {
			service.deleteCategory(id);
			errMess.deleteInBase(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
