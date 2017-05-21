package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {

}
