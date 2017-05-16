package fr.acdo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;

	private LocalDate birthday;

	private String email;

	private String password;

	// FIX ME => Penser à remplir la base en boolean
	private boolean active;

	private String image;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;

}
