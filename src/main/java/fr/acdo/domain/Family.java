package fr.acdo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Family {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Le nom de la famille ne peut pas être vide")
	@Size(min = 2, max = 45, message = "Le nom de la famille doit comporter entre 2 et 45 caractères")
	private String name;

}
