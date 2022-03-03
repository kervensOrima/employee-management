package com.projet.gestionemployee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeByMonthDTO {

	//Mois 
	private Long month ;
	
	//Nombre d'employee embauche
	private Long nbEmployee ;
	
	
}
