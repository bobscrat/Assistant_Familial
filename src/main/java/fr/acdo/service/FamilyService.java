package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Family;
import fr.acdo.repository.FamilyDao;

//Dans le service, on définit les méthodes du repository (=DAO)

@Service
public class FamilyService {
	
	//Je fais appel à mon Role Dao 
	private FamilyDao repoFam;	
	
	//Constructeur qui est lancé automatiquement avec l'annotation
	@Autowired 
	public FamilyService(FamilyDao family) {
		this.repoFam = family;
	}
	
	//Je crée un méthode qui recupere dans une liste tous les roles
	public List<Family> getAllFamily() {
		return repoFam.findAll();
	}
	
	//Méthode qui permet de récuperer un role avec id en parametre
	public Family getById(Long id){
		return repoFam.findOne(id);
	}
	
	public Family createFamily(Family family){
		return repoFam.save(family);
	}
}
