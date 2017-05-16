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

import fr.acdo.domain.Periodicity;
import fr.acdo.service.PeriodicityService;

@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
@RestController
public class PeriodicityController {

	private PeriodicityService service;

	@Autowired
	public PeriodicityController(PeriodicityService service) {
		this.service = service;
	}

	@GetMapping("/api/periodicities")
	public List<Periodicity> listPeriodicities() {
		List<Periodicity> list = null;
		try {
			list = service.getAllPeriodicities();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("/api/periodicity/{id}")
	public Periodicity getPeriodicity(@PathVariable Long id) {
		Periodicity periodicity = null;
		try {
			periodicity = service.getPeriodicityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return periodicity;
	}

	@PostMapping("/api/periodicity")
	public Periodicity savePeriodicity(@RequestBody @Valid Periodicity periodicity) {
		Periodicity newPeriodicity = null;
		try {
			newPeriodicity = service.savePeriodicity(periodicity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newPeriodicity;
	}

	@PutMapping("/api/periodicity")
	public Periodicity updatePeriodicity(@RequestBody @Valid Periodicity periodicity) {
		Periodicity newPeriodicity = null;
		try {
			newPeriodicity = service.savePeriodicity(periodicity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newPeriodicity;
	}

	@DeleteMapping("/api/periodicity/{id}")
	public void deletePeriodicity(@PathVariable Long id) {
		try {
			service.deletePeriodicity(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
