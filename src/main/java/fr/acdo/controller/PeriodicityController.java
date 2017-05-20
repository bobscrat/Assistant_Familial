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

import fr.acdo.domain.Periodicity;
import fr.acdo.exception.CustomException;
import fr.acdo.log.ErrorMessages;
import fr.acdo.service.PeriodicityService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/periodicities")
public class PeriodicityController {

	private ErrorMessages errMess = new ErrorMessages();
	private PeriodicityService service;

	@Autowired
	public PeriodicityController(PeriodicityService cateService) {
		this.service = cateService;
	}

	@GetMapping
	public List<Periodicity> listPeriodicities() {
		List<Periodicity> list = service.getAllPeriodicities();
		if (null == list) {
			errMess.getAll(getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La liste des périodicités n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Periodicity getPeriodicity(@PathVariable Long id) {

		Periodicity periodicity = service.getPeriodicityById(id);
		if (null == periodicity) {
			errMess.getById(this.getClass(), id, new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La périodicité avec l'id = " + id + " n'a pas été trouvée");
		}

		return periodicity;
	}

	@PostMapping
	public Periodicity savePeriodicity(@RequestBody @Valid Periodicity periodicity) {
		Periodicity newPeriodicity = service.savePeriodicity(periodicity);
		if (null == periodicity) {
			errMess.saveInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La périodicité n'a pas été enregistrée");
		}
		return newPeriodicity;
	}

	@PutMapping
	public Periodicity updatePeriodicity(@RequestBody @Valid Periodicity periodicity) {
		Periodicity newPeriodicity = service.savePeriodicity(periodicity);
		if (null == periodicity) {
			errMess.updateInBase(this.getClass(), new Object() {
			}.getClass().getEnclosingMethod().getName());
			throw new CustomException("La périodicité n'a pas été mise à jour.");
		}
		return newPeriodicity;
	}

}
