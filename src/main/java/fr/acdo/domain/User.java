package fr.acdo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private LocalDate birthday;

	private String email;

	private String password;

	// FIX ME => Penser à remplir la base en boolean
	private boolean active;

	private String image;

	@ManyToOne
	@JoinColumn(name = "Role_id")
	@JsonManagedReference
	private Role roletest;

	@ManyToOne
	@JoinColumn(name = "Family_id")
	@JsonManagedReference
	private Family familytest;

}
