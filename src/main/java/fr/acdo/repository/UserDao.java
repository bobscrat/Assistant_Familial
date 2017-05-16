package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.User;



public interface UserDao extends JpaRepository <User, Long>{

}
