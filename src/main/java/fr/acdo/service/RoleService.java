package fr.acdo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.acdo.domain.Role;
import fr.acdo.repository.RoleRepository;

//Dans le service, on définit les méthodes du repository (=DAO)

@Service
public class RoleService {

	// Je fais appel à mon Role Dao
	private RoleRepository repo;

	// Constructeur qui est lancé automatiquement avec l'annotation
	@Autowired
	public RoleService(RoleRepository role) {
		this.repo = role;
	}

	// Je crée un méthode qui recupere dans une liste tous les roles
	public List<Role> getAllRoles() {
		return repo.findAll();
	}

	// Méthode qui permet de récuperer un role avec id en parametre
	public Role getRoleById(Long id) {
		return repo.findOne(id);
	}

	public Role saveRole(Role role) {
		return repo.save(role);
	}
}
