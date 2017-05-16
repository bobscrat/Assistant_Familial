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
	@NotNull(message = "Le numéro de téléphone ne peut pas être vide")
	@Size(min = 2, max = 45, message = "Le numéro de téléphone doit comporter entre 10 chiffres")
	private String phone;
	@NotNull(message = "L'email du contact ne peut pas être vide")
	@Size(min = 2, max = 45, message = "L'email du contact doit comporter entre 10 et 45 caractères")
	private String email;
	private String address;
	@NotNull(message = "La profession du contact ne peut pas être vide")
	@Size(min = 2, max = 45, message = "La profession du contact doit comporter entre 2 et 45 caractères")
	private String profession;
	private String comment;

	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;

}
