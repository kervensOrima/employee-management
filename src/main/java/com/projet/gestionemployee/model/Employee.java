package com.projet.gestionemployee.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Employee")

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3380997900186431598L;

	@Id
	@SequenceGenerator(allocationSize = 1 ,sequenceName ="employee_Sequence", initialValue = 10000 , name = "employee_Sequence"  )
    @GeneratedValue( generator = "employee_Sequence")
	private Long id ;
	
	@Column(length = 255 , nullable = false , unique = true)
	private String code ;
	
	
	@Column(length = 50 , nullable = false )
	@NotNull
	@Size( min = 3 , max = 50 , message="First name invalid")
	private String nom ;
	
	@Column(length = 50 , nullable = false )
	@NotNull
	@Size( min = 3 , max = 50 , message="Last name invalid")
	private String prenom ;
	
	@Column(length = 11 , nullable = false )
	@NotNull
	private Double salaire ;
	
	@Column(name = "contrat_actuelle")
	private boolean contrat_actuelle ;
	
	@Column(length = 50 , nullable = false )
	@NotNull
	@Size( min = 3 , max = 50 , message="Type contrat invalid")
	private String typeContrat ;
	
	@JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	private Date dateEmbauche ;
	
	@Column(length = 50 , nullable = false )
	@NotNull
	@Size( min = 2 , max = 50 , message="Etat invalid")
	private String etat ;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany( mappedBy = "employee" , fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
	private List<Adress> adresses ;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany( mappedBy = "employee" , cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
	private List<Phone> phones ;
}
