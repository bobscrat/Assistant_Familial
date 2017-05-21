package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Family;
import fr.acdo.repository.FamilyRepository;

//Dans le service, on définit les méthodes du repository (=DAO)

@Service
public class FamilyService {

	// Je fais appel à mon Role Dao
	private FamilyRepository repoFam;

	// Constructeur qui est lancé automatiquement avec l'annotation
	@Autowired
	public FamilyService(FamilyRepository family) {
		this.repoFam = family;
	}

	// Je crée un méthode qui recupere dans une liste tous les roles
	public List<Family> getAllFamilies() {
		return repoFam.findAll();
	}

	// Méthode qui permet de récuperer un role avec id en parametre
	public Family getFamilyById(Long id) {
		return repoFam.findOne(id);
	}

	public Family saveFamily(Family family) {
		return repoFam.save(family);
	}
}
