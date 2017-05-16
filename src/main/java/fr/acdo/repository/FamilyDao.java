package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Family;

public interface FamilyDao extends JpaRepository <Family, Long>{
 

}
