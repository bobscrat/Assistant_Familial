// Olga
package fr.acdo.domain;

import javax.persistence.Column;
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
public class Priority {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Le champ 'nom' ne peut pas être vide")
	@Size(min = 2, max = 45, message = "Le nom doit faire au moins 2 caractères")
	@Column(unique = true)
	private String name;

}
