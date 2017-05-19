package fr.acdo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Le champ 'nom' ne peut pas être vide")
	// @Unique(message = "Le champ 'nom' doit être unique")
	@Size(min = 2, max = 45, message = "Le nom doit faire au moins 2 caractères")
	private String name;

	@ManyToOne
	@JoinColumn(name = "family_id")
	private Family family;
}
