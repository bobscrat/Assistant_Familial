package fr.acdo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event_0 {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull(message = "Le champ 'nom' ne peut pas être vide")
	private String name;
	
	@NotNull(message = "Le champ 'predefined' ne peut pas être vide")
	private Boolean predefined;
	
	@NotNull(message = "Le champ 'done' ne peut pas être vide")
	private Boolean done;
	
	private String comment;

//	format du Json POST = "deadline": "2017-06-13T18:00:00" cad yyyy-mm-ddThh:mm:ss
//	format du Json GET = "deadline":[2017,6,13,18,0] cad [yyyy,m,d,h,min]
	private LocalDateTime deadline;

	// Integer ou Float pour que la valeur puisse être nulle
	private Integer periodicityValue;
	private Float estimatedBudget;
	private Float realizedBudget;

	private Boolean hasChild;
	
	@OneToOne
	@JoinColumn(name = "id_parent")
	private Event_0 parentEvent;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
	private Long user_id;
	
	@NotNull(message = "Le champ 'category' ne peut pas être vide")
//	@ManyToOne
//	@JoinColumn(name = "category_id")
//	private Category category;
	private Long category_id;
	
//	@ManyToOne
//	@JoinColumn(name = "project_id")
//	private Project project;
	private Long project_id;
//	
//	@ManyToOne
//	@JoinColumn(name = "contact_id")
//	private Contact contact;
	private Long contact_id;
	
//	@ManyToOne
//	@JoinColumn(name = "periodicity_id")
//	private Periodicity periodicity;
	private Long periodicity_id;
	
	@ManyToOne
	@JoinColumn(name = "priority_id")
	private Priority priority;
	
//	@UpdateTimestamp
//	private Timestamp lastUpdate;
	
}
