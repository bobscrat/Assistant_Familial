package fr.acdo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull(message = "Le nom du contact ne peut pas être vide")
	@Size(min = 2, max = 45, message = "Le nom du contact doit comporter entre 2 et 45 caractères")
	private String name;
	private String first_name;

	private String phone;

	private String email;
	private String address;

	private String profession;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;

}
