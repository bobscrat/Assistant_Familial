package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	List<Contact> findByFamilyIdOrderByName(Long contactId);

}
