package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.User;



public interface UserRepository extends JpaRepository <User, Long>{

}
