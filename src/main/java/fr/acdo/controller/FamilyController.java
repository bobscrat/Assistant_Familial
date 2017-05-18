package fr.acdo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.acdo.domain.Family;
import fr.acdo.service.FamilyService;

@RestController
@CrossOrigin(origins = "*") // à supprimer en prod
public class FamilyController {

	private FamilyService familyService;

	public FamilyController(FamilyService familyService) {
		this.familyService = familyService;
	}

	@GetMapping("api/family")
	public List<Family> listFamily() {
		List<Family> myList = null;
		try {
			myList = familyService.getAllFamilies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myList;
	}

	@GetMapping("api/family/{id}")
	public Family getAFamily(@PathVariable Long id) {
		Family myFamily = null;
		try {
			myFamily = familyService.getFamilyById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myFamily;
	}

	@PostMapping("api/family")
	public Family addFamily(@RequestBody Family family) {
		return familyService.addFamily(family);
	}

}
