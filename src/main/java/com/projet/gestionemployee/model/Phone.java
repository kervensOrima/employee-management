package com.projet.gestionemployee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Table(name="Phone")

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3256482772708555901L;

	@Id
	@SequenceGenerator( allocationSize = 1 , name = "Phone_sequence" , sequenceName = "Phone_sequence" )
	@GeneratedValue( generator = "Phone_sequence")
	private Long id ;
	
	@Column( length = 20 , nullable = false )
	@NotNull
	@Size(min = 8 , max = 20 )
	private String phone ;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee ;
	
}
