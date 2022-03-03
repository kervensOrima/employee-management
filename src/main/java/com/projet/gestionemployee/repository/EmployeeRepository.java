package com.projet.gestionemployee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.EmployeeByMonthDTO;

@Repository
public interface EmployeeRepository extends JpaRepository< Employee, Long> {

	public List<Employee>  findByEtat(String etat);
	
	public List<Employee>  findByTypeContrat(String typeContrat);
	
	@Query( value = "SELECT e.* FROM Employee e WHERE e.contrat_actuelle = :contrat", nativeQuery = true )
	public List<Employee> findByNoContainsContrat(@Param(value = "contrat") boolean contrat) ;
	
	
	@Query( value = "SELECT count(*) from employee where lower(etat)= lower(:etat)" , nativeQuery = true )
	public Long numberOfEmployeesActif(@Param(value = "etat") String etat) ;
	
	
	@Query( value = "SELECT count(*) from Employee where lower(etat)= lower(:etat)" , nativeQuery = true )
	public Long numberOfEmployeesInactif(@Param(value = "etat") String etat ) ;
	
	@Query( value = "SELECT COUNT(*) FROM employee WHERE lower(type_contrat) = lower(:typeContrat)" , nativeQuery = true )
	public Long NumberOfEmployeeCDD(@Param( value = "typeContrat") String typeContrat ) ;
	
	@Query( value = "SELECT COUNT(*) FROM employee WHERE lower(type_contrat) = lower(:typeContrat)" , nativeQuery = true )
	public Long NumberOfEmployeeCDI(@Param( value = "typeContrat") String typeContrat  ) ;
	
	@Query( value ="SELECT  extract(month from date_embauche) month , count(*) nbEmployee from employee GROUP by extract(month from date_embauche) , extract(month from date_embauche)" , nativeQuery = true )
	public List<EmployeeByMonthDTO> numberOfEmployeesEmbaucherByMonth() ;
	
	@Query( value = "SELECT count(*) \"Nombre d'employee\" from employee where extract(month from date_embauche) = :month" , nativeQuery = true )
	public Long numberOfEmployeesByMonth(@Param(value = "month") Long month) ;
}
