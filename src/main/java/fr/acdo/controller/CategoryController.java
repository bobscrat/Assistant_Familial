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

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	// méthodes suivantes : listes, recup un projet, enregistrement et màj
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Category> listCategories() {
		List<Category> list = null;
		try {
			list = service.getAllCategories();
		} catch (CannotCreateTransactionException e) { // je catch si pas accès
														// BDD
			System.out.println("Je dis que la BDD est DEAD");
			errMess.getAll(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CannotCreateTransactionException("SERVEUR DEAD.");
		}
		return list;
	}

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

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Category saveCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
		// création en amont
		Category newCategory = new Category();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			System.out.println("si un des deux est NULL");
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), "Champs mal renseignés");
			throw new IllegalArgumentException(
					"La catégorie n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			System.out.println("premier acte de création");
			newCategory = service.saveCategory(category);
			System.out.println("deuxième acte après action du service");

		} catch (DataIntegrityViolationException e) {// on catch l'erreur de
														// contrainte intégrité
			System.out.println("EXISTE DEJAAAAAA avant le log");
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			System.out.println("EXISTE DEJAAAAAA avant le throw");
			System.out.println(e.getLocalizedMessage());
			throw new ConstraintViolationException("La catégorie existe déjà", Collections.emptySet());
		}
		return newCategory;
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Category updateCategory(@RequestBody @Valid Category category, BindingResult bindingResult) {
		// création en amont
		Category newCategory = new Category();
		// pour pouvoir gérer si @Valid renvoit des erreurs
		if (bindingResult.hasErrors()) {
			System.out.println("si un des deux est NULL");
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), " Champs mal renseignés");
			throw new IllegalArgumentException(
					"La catégorie n'a pas été enregistrée car au moins un champ est invalide.");
		}
		try {
			System.out.println("premier acte de création");
			newCategory = service.saveCategory(category);
			System.out.println("deuxième acte après action du service");

		} catch (DataIntegrityViolationException e) { // on catch l'erreur de
														// contrainte intégrité
			System.out.println("EXISTE DEJAAAAAA avant le log");
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName(), e.toString());
			System.out.println("EXISTE DEJAAAAAA avant le throw");
			System.out.println(e.getLocalizedMessage());
			throw new ConstraintViolationException("La catégorie existe déjà", Collections.emptySet());
		}
		return newCategory;
	}
}
