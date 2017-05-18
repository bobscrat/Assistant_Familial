package fr.acdo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Priority;
import fr.acdo.exception.CustomException;
import fr.acdo.service.PriorityService;

@CrossOrigin(origins = "*") // à supprimer en prod
@RestController
@RequestMapping("/api/priorities")
public class PriorityController {

	private PriorityService service;

	public PriorityController(PriorityService service) {
		this.service = service;
	}

	@GetMapping
	public List<Priority> listPriorities() {
		List<Priority> list = service.getAllPriorities();
		if (null == list) {
			throw new CustomException("La liste de priorités n'a pas été trouvée");
		}
		return list;
	}

	@GetMapping("/{id}")
	public Priority getPriority(@PathVariable Long id) {
		Priority priority = service.getPriorityById(id);
		if (null == priority) {
			throw new CustomException("La priorité avec l'id = " + id + " n'a pas été trouvée");
		}
		return priority;
	}

	@PostMapping
	public Priority savePriority(@RequestBody @Valid Priority priority) {
		Priority newPriority = service.savePriority(priority);
		if (null == newPriority) {
			throw new CustomException("La priorité n'a pas été enregistrée");
		}
		return newPriority;
	}

	@PutMapping
	public Priority updatePriority(@RequestBody @Valid Priority priority) {
		Priority newPriority = service.savePriority(priority);
		if (null == newPriority) {
			throw new CustomException("La priorité avec l'id = " + priority.getId() + " n'a pas été mise à jour");
		}
		return newPriority;
	}

}
