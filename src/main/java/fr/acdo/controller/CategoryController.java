package fr.acdo.controller;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Category;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.CategoryService;

@CrossOrigin(origins = "*") // to be deleted in prod
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	// instantiation of the classes
	private ErrorMessages errMess = new ErrorMessages();
	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	/**
	 * Get all Categories
	 * 
	 * @return a list of Categories
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> listCategories() {
		List<Category> list = null;
		try {
			list = service.getAllCategories();
		} catch (CannotCreateTransactionException e) { // no database access
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

	/**
	 * Get a Category by id
	 * 
	 * @param id
	 * @return a Category
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Category getCategory(@PathVariable Long id) {
		Category category = service.getCategoryById(id);
		if (null == category) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new NoSuchElementException("La catégorie demandée n'a pas été trouvée.");
		}
		return category;
	}

	/**
	 * Create a Category in the database
	 * 
	 * @param category
	 * @return a Category
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Category saveCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
		Category newCategory = new Category();
		// to manage @Valid exceptions
		if (bindingResult.hasErrors()) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"La catégorie n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newCategory = service.saveCategory(category);
		} catch (DataIntegrityViolationException e) { // integrity violation
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La catégorie existe déjà", Collections.emptySet());
		}
		return newCategory;
	}

	/**
	 * Update a Category in the database
	 * 
	 * @param category
	 * @return a Category
	 */
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Category updateCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {

		Category newCategory = new Category();
		// to manage @Valid exceptions
		if (bindingResult.hasErrors()) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"La catégorie n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			newCategory = service.saveCategory(category);
		} catch (DataIntegrityViolationException e) { // integrity violation
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			throw new ConstraintViolationException("La catégorie existe déjà", Collections.emptySet());
		}
		return newCategory;
	}
}
