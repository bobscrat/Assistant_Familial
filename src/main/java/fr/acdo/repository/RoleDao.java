package fr.acdo.repository;
import fr.acdo.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository sert à recuperer toutes les méthodes(ex: findAll findOne,..)
//embarque les méthodes du repository et de Crud repository
public interface RoleDao extends JpaRepository <Role, Long>{

}
