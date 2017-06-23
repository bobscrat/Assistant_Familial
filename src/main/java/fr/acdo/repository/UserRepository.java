package fr.acdo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.acdo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);

	public User findByEmail(String email);

	public List<User> findByFamilyId(Long familyId);

	public List<User> findByFamilyIdAndActive(Long familyId, Boolean boolean1);

	public List<User> findByFamilyIdOrderByFirstName(Long familyId);

	public List<User> findByFamilyIdAndActiveOrderByFirstName(Long familyId, Boolean boolean1);

}
