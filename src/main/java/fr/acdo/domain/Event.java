package fr.acdo.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Le champ 'nom' ne peut pas être vide")
	private String name;

	@NotNull(message = "Le champ 'predéfini' ne peut pas être vide")
	private Boolean predefined;

	@NotNull(message = "Le champ 'réalisé' ne peut pas être vide")
	private Boolean done;

	private String comment;

	// format du Json POST = "deadline": "2017-06-13T18:00:00" cad
	// yyyy-mm-ddThh:mm:ss
	// format du Json GET = "deadline":[2017,6,13,18,0] cad [yyyy,m,d,h,min]
	private LocalDateTime deadline;

	// Integer ou Float pour que la valeur puisse être nulle
	private Integer periodicityValue;
	private Float estimatedBudget;
	private Float realizedBudget;

	private Boolean hasChild;

	@OneToOne
	@JoinColumn(name = "parent_id")
	private Event parentEvent;

	@NotNull(message = "Le champ 'bénéficiaire' ne peut pas être vide")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull(message = "Le champ 'categorie' ne peut pas être vide")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "contact_id")
	private Contact contact;

	@ManyToOne
	@JoinColumn(name = "periodicity_id")
	private Periodicity periodicity;

	@ManyToOne
	@JoinColumn(name = "priority_id")
	private Priority priority;

	@UpdateTimestamp
	private Timestamp lastUpdate;

}
