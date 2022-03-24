package com.projet.gestionemployee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.gestionemployee.exceptions.EmployeeException;
import com.projet.gestionemployee.interfaces.IEmployee;
import com.projet.gestionemployee.model.Adress;
import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.EmployeeByMonthDTO;
import com.projet.gestionemployee.model.Phone;
import com.projet.gestionemployee.repository.AdressRepository;
import com.projet.gestionemployee.repository.EmployeeRepository;
import com.projet.gestionemployee.repository.PhoneRepository;

@Service
@Transactional
public class EmployeeService implements IEmployee {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	public EmployeeService(EmployeeRepository employeeRepository, AdressRepository adressRepository,
			PhoneRepository phoneRepository) {
		this.employeeRepository = employeeRepository;
		this.adressRepository = adressRepository;
		this.phoneRepository = phoneRepository;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return this.employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(Long employeeID) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = this.employeeRepository.findById(employeeID);

		if (employee == null) {

			throw new EmployeeException("Employee with id  " + employeeID + " doens't exist in database!!!");
		}
		return employee;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.findById(employee.getId());

		// save the phone the employee
		java.util.List<Phone> phones = employee.getPhones();
		if (phones != null)
			this.phoneRepository.saveAll(phones);

		// save the adresses of the employee
		java.util.List<Adress> adresses = employee.getAdresses();
		if (adresses != null)
			this.adressRepository.saveAll(adresses);

		return this.employeeRepository.save(employee);

	}

	@Override
	public void deleteEmployee(Long employeeID) {
		this.findById(employeeID);
		this.employeeRepository.deleteById(employeeID);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		Employee emp = this.employeeRepository.save(employee) ;
		employee.setId( emp.getId() );
		
		System.out.println( "\n\n\n\n\n\n employee info " +  employee.getId()   ) ;
		
		// save the phone the employee
		java.util.List<Phone> phones = employee.getPhones();
		if (phones != null)
		{
//			this.phoneRepository.saveAll(phones);
			
			phones.forEach( p ->{
				this.phoneRepository.save( new Phone( null , p.getPhone() , employee)  ) ;
			});
		}

		// save the adresses of the employee
		java.util.List<Adress> adresses = employee.getAdresses();
		if (adresses != null) {
			
			adresses.forEach( a -> {
				this.adressRepository.save( new Adress(null, a.getRue() , a.getVille() , a.getCodePostal() , employee)  ) ;
			});
		}
			

		return emp ;
	}

	@Override
	public List<Employee> findByEtat(String etat) {
		// TODO Auto-generated method stub
		return this.employeeRepository.findByEtat(etat);
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
		return this.employeeRepository.count();
	}

	@Override
	public Long numberOfEmployeesActif() {
		// TODO Auto-generated method stub
		return this.employeeRepository.numberOfEmployeesActif("actif");
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
		return this.employeeRepository.numberOfEmployeesEmbaucherByMonth();
	}

	@Override
	public List<EmployeeByMonthDTO> numberOfEmployeesByMonth(Long year) {
		// TODO Auto-generated method stub
		List<EmployeeByMonthDTO> list = new ArrayList<>();

		for (Long month = 1L; month <= 12; month++) {
			list.add(new EmployeeByMonthDTO(month, this.employeeRepository.numberOfEmployeesByMonth(month, year)));
		}

		return list;
	}

}
