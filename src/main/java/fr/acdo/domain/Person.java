package fr.acdo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Le champ 'prénom' ne peut pas être vide")
	@Size(min = 2, max = 45, message = "Le prénom dois comporter au moins 2 caractères")
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
