package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Category;
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.CategoryService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService cateService) {
		this.service = cateService;
	}

	// méthodes suivantes : listes, recup un projet, enregistrement et màj
	@GetMapping
	public List<Category> listCategories() {
		List<Category> list = service.getAllCategories();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des catégories n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {

		Category category = service.getCategoryById(id);
		if (null == category) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La catégory avec l'id = " + id + " n'a pas été trouvée");
		}

		return category;
	}

	@PostMapping
	public Category saveCategory(@RequestBody @Valid Category category) {
		Category newCategory = service.saveCategory(category);
		if (null == category) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La catégorie n'a pas été enregistrée");
		}
		return newCategory;
	}

	@PutMapping
	public Category updateCategory(@RequestBody @Valid Category category) {
		Category newCategory = service.saveCategory(category);
		if (null == category) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La catégorie n'a pas été mise à jour.");
		}
		return newCategory;
	}

}
