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

import fr.acdo.domain.Priority;
import fr.acdo.service.PriorityService;

@CrossOrigin(origins = "http://localhost:3000") // à supprimer en prod
@RestController
public class PriorityController {

	private PriorityService service;

	@Autowired
	public PriorityController(PriorityService service) {
		this.service = service;
	}

	@GetMapping("/api/priorities")
	public List<Priority> listPriorities() {
		List<Priority> list = null;
		try {
			list = service.getAllPriorities();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("/api/priorities/{id}")
	public Priority getPriority(@PathVariable Long id) {
		Priority priority = null;
		try {
			priority = service.getPriorityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return priority;
	}

	@PostMapping("/api/priorities")
	public Priority savePriority(@RequestBody @Valid Priority priority) {
		Priority newPriority = null;
		try {
			newPriority = service.savePriority(priority);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newPriority;
	}

	@PutMapping("/api/priorities")
	public Priority updatePriority(@RequestBody @Valid Priority priority) {
		Priority newPriority = null;
		try {
			newPriority = service.savePriority(priority);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newPriority;
	}

	@DeleteMapping("/api/priorities/{id}")
	public void deleteCategory(@PathVariable Long id) {
		try {
			service.deletePriority(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
