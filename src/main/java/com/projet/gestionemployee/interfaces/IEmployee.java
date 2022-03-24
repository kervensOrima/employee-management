package com.projet.gestionemployee.interfaces;

import java.util.List;
import java.util.Optional;

import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.EmployeeByMonthDTO;

public interface IEmployee {

	public List<Employee> findAll() ;
	
	public Optional<Employee> findById(Long employeeID) ;
	
	public Employee  updateEmployee(Employee employee) ;
	
	public Employee saveEmployee(Employee employee) ;
	
	public void deleteEmployee( Long employeeID) ;
	
	public List<Employee>  findByEtat(String etat) ;
	
	public List<Employee> findByTypeContrat(String typeContrat) ;
	
	public List<Employee> findNoContainsContrat(boolean contrat)  ;
	
	public Long numberOfEmployees() ;
	
	public Long numberOfEmployeesActif() ;
	
	public Long numberOfEmployeesInactif() ;
	
	//Quantite d'employee par contrat CDD
	public Long numberOfEmployeesCDD() ;
	
	//Quantite d'employee par contrat CID
	public Long numberOfEmployeesCID() ;
	
	public List<EmployeeByMonthDTO> numberOfEmployeesEmbaucherByMonth() ;
	
	public List<EmployeeByMonthDTO> numberOfEmployeesByMonth(Long year) ;
	
	
}
