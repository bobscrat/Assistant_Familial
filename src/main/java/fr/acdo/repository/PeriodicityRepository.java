package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Periodicity;

// interface + le bean + type de l'ID
public interface PeriodicityRepository extends JpaRepository<Periodicity, Long> {

	List<Periodicity> findAllByOrderById();

}
