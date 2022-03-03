package com.projet.gestionemployee.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.gestionemployee.exceptions.EmployeeException;
import com.projet.gestionemployee.interfaces.IEmployee;
import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.EmployeeByMonthDTO;
import com.projet.gestionemployee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService implements IEmployee {
	
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository ;
	}
	
	

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll() ;
	}

	@Override
	public Optional<Employee> findById(Long employeeID) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = this.employeeRepository.findById(employeeID) ;
		
		if( employee == null ) {
			throw new EmployeeException( "Employee with id  " + employeeID + " doens't exist in database!!!" ) ;
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.findById( employee.getId() ) ;
		return this.employeeRepository.save( employee ) ;		
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
//		if( employee.getDateEmbauche().before( new Date() )  ) {
//			System.out.println( "Hello world with me !!!!!!!!");
//			
//		}
		
		return this.employeeRepository.save( employee );
	}

	@Override
	public List<Employee> findByEtat(String etat) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByEtat(etat) ;
	}

	@Override
	public List<Employee> findByTypeContrat(String typeContrat) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByTypeContrat(typeContrat);
	}

	@Override
	public List<Employee> findNoContainsContrat(boolean contrat) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByNoContainsContrat(contrat);
	}

	@Override
	public Long numberOfEmployees() {
		// TODO Auto-generated method stub
		return this.employeeRepository.count() ;
	}

	@Override
	public Long numberOfEmployeesActif() {
		// TODO Auto-generated method stub
		return this.employeeRepository.numberOfEmployeesActif( "actif")  ;
	}

	@Override
	public Long numberOfEmployeesInactif() {
		// TODO Auto-generated method stub
		return this.employeeRepository.numberOfEmployeesInactif("inactif");
	}

	@Override
	public Long numberOfEmployeesCDD() {
		// TODO Auto-generated method stub
		return this.employeeRepository.NumberOfEmployeeCDD("cdd");
	}

	@Override
	public Long numberOfEmployeesCID() {
		// TODO Auto-generated method stub
		return this.employeeRepository.NumberOfEmployeeCDI("cdi");
	}

	@Override
	public List<EmployeeByMonthDTO> numberOfEmployeesEmbaucherByMonth() {
		// TODO Auto-generated method stub
		return this.employeeRepository.numberOfEmployeesEmbaucherByMonth() ;
	}

	@Override
	public Long numberOfEmployeesByMonth(Long month) {
		// TODO Auto-generated method stub
		return this.employeeRepository.numberOfEmployeesByMonth( month );
	}
	
	

}
