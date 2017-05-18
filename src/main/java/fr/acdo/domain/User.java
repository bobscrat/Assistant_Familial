package fr.acdo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String firstName;

	private LocalDate birthday;

	private String email;

	private String password;

	private boolean active;

	private String image;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;

}
