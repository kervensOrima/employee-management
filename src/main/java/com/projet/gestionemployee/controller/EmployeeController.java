package com.projet.gestionemployee.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.EmployeeByMonthDTO;
import com.projet.gestionemployee.service.EmployeeService;

@RestController
@RequestMapping( path = "/employee-management-app/" , name = "employee management principal root")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService ;
	
	
	@GetMapping(path = "/employees" , name = "Find all employee")
	public ResponseEntity<List<Employee>> findAllEmployee() {
		return new ResponseEntity<List<Employee>>( this.employeeService.findAll() , HttpStatus.OK) ;
	}
	
	
	@GetMapping( path = "/employee/{employeeID}" , name=" Find an employee in database")
	public ResponseEntity<Optional<Employee>> findOne( @PathVariable Long employeeID) {
		Optional<Employee> employee = null ;
		try {
			employee = this.employeeService.findById(employeeID) ;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println( e.getMessage() );
		}
		return new ResponseEntity<Optional<Employee>>(employee, HttpStatus.OK)  ;
	}
	
	@PutMapping( path = "/employee" , name="Update an employee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp = new Employee() ;
		try {

			emp = this.employeeService.updateEmployee(employee);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		return new ResponseEntity<Employee>( emp , HttpStatus.CREATED ) ;
	}
	
	@PostMapping( path = "/employee" , name = "Register an employee in database!!")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		//Generer un code pou l'utilisateur
		employee.setCode( UUID.randomUUID().toString() ) ;
		return new ResponseEntity<Employee>( this.employeeService.saveEmployee(employee), HttpStatus.CREATED ) ;
	}
	
	@DeleteMapping(path = "/employee/{employeeID}")
	public ResponseEntity<?> deleteEmployee( @PathVariable(name = "employeeID") Long employeeID ){
		try {
			this.employeeService.deleteEmployee(employeeID) ;
			
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Exception>( HttpStatus.NO_CONTENT) ;
		}
		
		return new ResponseEntity<>( HttpStatus.NO_CONTENT ) ;
	}
	
	
	@RequestMapping( path = "/employees-by-etat/{etat}" , name="Find employees by etat" , method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> findEmployeeByEtat(@PathVariable String etat) {
		return new ResponseEntity<List<Employee>>( this.employeeService.findByEtat(etat), HttpStatus.OK) ;
	}
	
	
	@RequestMapping( path = "/employees-by-type-contrat/{type_contrat}" , name=" Find employees by type contrat" , method = RequestMethod.GET )
	public ResponseEntity<List<Employee>> findByTypeContrat(@PathVariable String type_contrat) {
		return new ResponseEntity<List<Employee>>( this.employeeService.findByTypeContrat(type_contrat), HttpStatus.OK) ;
	}
	
	
	@RequestMapping( path = "/employees-not-contains-contrat/{isContrat}" , name=" Find employees not contains contrat" , method = RequestMethod.GET )
	public ResponseEntity<List<Employee>> findNoContainsContrat(@PathVariable(name = "isContrat") boolean isContrat) {
		return new ResponseEntity<List<Employee>>( this.employeeService.findNoContainsContrat(isContrat)  , HttpStatus.OK) ;
	}
	
	@RequestMapping( path = "/employees-quantity" , name=" Find number of employee" , method = RequestMethod.GET )
	public ResponseEntity<Long> numberOfEmployees() {
		return new ResponseEntity<Long>( this.employeeService.numberOfEmployees(), HttpStatus.OK ) ;
	}
	
	@RequestMapping( path = "/employees-actif" , name=" Find number of actif employee" , method = RequestMethod.GET )
	public ResponseEntity<Long> numberOfEmployeesActif() {
		return new ResponseEntity<Long>( this.employeeService.numberOfEmployeesActif(), HttpStatus.OK ) ;
	}
	
	@RequestMapping( path = "/employees-inactif" , name=" Find number of inactif employee" , method = RequestMethod.GET )
	public ResponseEntity<Long> numberOfEmployeesInactif() {
		return new ResponseEntity<Long>( this.employeeService.numberOfEmployeesInactif(), HttpStatus.OK ) ;
	}
	

	@RequestMapping( path = "/employees-cdd" , name=" Find number of inactif employee" , method = RequestMethod.GET )
	public ResponseEntity<Long> numberOfEmployeesCDD() {
		return new ResponseEntity<Long>( this.employeeService.numberOfEmployeesCDD(), HttpStatus.OK ) ;
	}
	
	@RequestMapping( path = "/employees-cid" , name=" Find number of inactif employee" , method = RequestMethod.GET )
	public ResponseEntity<Long> numberOfEmployeesCID() {
		return new ResponseEntity<Long>( this.employeeService.numberOfEmployeesCID(), HttpStatus.OK ) ;
	}
	
	@GetMapping( path ="/employee-embauchee-by-month" , name = "Find employee embauche by month")
	public ResponseEntity<List<EmployeeByMonthDTO>> numberOfEmployeesEmbaucherByMonth(){
		return new ResponseEntity<List<EmployeeByMonthDTO>>( this.employeeService.numberOfEmployeesEmbaucherByMonth() , HttpStatus.OK ) ;
	}
	
	@RequestMapping( path="/employee-by-month/{year}" , name="Get number of employee enroll (hire) by month for a specific year")
	public ResponseEntity<List<EmployeeByMonthDTO>> numberOfEmployeesByMonth(@PathVariable Long year) {
		return new ResponseEntity<List<EmployeeByMonthDTO>> ( this.employeeService.numberOfEmployeesByMonth(year) , HttpStatus.OK ) ;
	}
	
}
