package fr.acdo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
