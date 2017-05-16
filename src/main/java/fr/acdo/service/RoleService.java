package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Role;
import fr.acdo.repository.RoleDao;

//Dans le service, on définit les méthodes du repository (=DAO)

@Service
public class RoleService {
	
	//Je fais appel à mon Role Dao 
	private RoleDao repo;	
	
	//Constructeur qui est lancé automatiquement avec l'annotation
	@Autowired 
	public RoleService(RoleDao role) {
		this.repo = role;
	}
	
	//Je crée un méthode qui recupere dans une liste tous les roles
	public List<Role> getAllRole() {
		return repo.findAll();
	}
	
	//Méthode qui permet de récuperer un role avec id en parametre
	public Role getById(Long id){
		return repo.findOne(id);
	}
	
	public Role createRole(Role role){
		return repo.save(role);
	}
}
