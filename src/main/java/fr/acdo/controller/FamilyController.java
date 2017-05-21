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

import fr.acdo.domain.Family;
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.FamilyService;

@RestController
@CrossOrigin(origins = "*") // à supprimer en prod
@RequestMapping("/api/families")
public class FamilyController {
	// on instancie les classes
	private ErrorMessages errMess = new ErrorMessages();
	private FamilyService service;

	@Autowired
	public FamilyController(FamilyService famService) {
		this.service = famService;
	}

	@GetMapping
	public List<Family> listFamily() {
		List<Family> list = service.getAllFamilies();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des familles n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Family getFamily(@PathVariable Long id) {

		Family family = service.getFamilyById(id);
		if (null == family) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La famille avec l'id = " + id + " n'a pas été trouvée");
		}

		return family;
	}

	@PostMapping
	public Family saveFamily(@RequestBody @Valid Family family) {
		Family newFamily = service.saveFamily(family);
		if (null == family) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La famille n'a pas été enregistrée");
		}
		return newFamily;
	}

	@PutMapping
	public Family updateFamily(@RequestBody @Valid Family family) {
		Family newFamily = service.saveFamily(family);
		if (null == family) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La famille n'a pas été mise à jour.");
		}
		return newFamily;
	}

}
