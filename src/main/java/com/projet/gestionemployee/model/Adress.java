package com.projet.gestionemployee.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Adress")


@AllArgsConstructor 
@NoArgsConstructor
@ToString
@Builder
@Data
public class Adress {

	@Id
	@SequenceGenerator(allocationSize = 1 , initialValue = 1 , name = "Adress_sequence" , sequenceName = "Adress_sequence" )
	@GeneratedValue( generator = "Adress_sequence")
	private Long id ;
	
	@NotNull
	@Size( max = 50 , min = 3 , message = "Rue invalid" )
	@Column( length = 50 , nullable = false )
	private String rue ;
	
	@NotNull
	@Size( max = 50 , min = 3 , message = "Ville invalid" )
	@Column( length = 50 , nullable = false )
	private String ville ;
	
	@NotNull
	@Size( max = 50 , min = 3 , message = "Code postal invalid" )
	@Column( length = 50 , nullable = false )
	private String codePostal ;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
	@JoinColumn(name = "employee_id")
	private Employee employee ;
}
