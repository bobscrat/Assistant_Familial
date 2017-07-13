// Olga
package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {

	List<Priority> findAllByOrderById();

}
