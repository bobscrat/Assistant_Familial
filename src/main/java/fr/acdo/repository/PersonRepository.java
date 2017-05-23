package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
