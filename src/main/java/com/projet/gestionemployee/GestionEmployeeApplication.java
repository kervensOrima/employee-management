package com.projet.gestionemployee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.projet.gestionemployee.model.Adress;
import com.projet.gestionemployee.model.Employee;
import com.projet.gestionemployee.model.Phone;
import com.projet.gestionemployee.repository.AdressRepository;
import com.projet.gestionemployee.repository.EmployeeRepository;
import com.projet.gestionemployee.repository.PhoneRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class GestionEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEmployeeApplication.class, args);
	}
	
	
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	@Autowired
	private PhoneRepository phoneRepository ;
	
	@Autowired
	private AdressRepository adressRepository ;
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		
//		System.out.println( "Application start at "  );
//		
//		Stream.of( "(509) 4087-2241" , "(509) 3734-7727" , "(509) 4694-3094" , "(509) 3437-0908" , "+1 3636 828244").forEach( number-> {
//			phoneRepository.save( new Phone(null, number, null)  ) ;
//		});
//		
//		Stream.of( "Port-au-prince" , "Bainet" , "Fond des negres").forEach( (v ) ->{
//			adressRepository.save( new Adress( null , v , v , v , null ) ) ;
//		});
//		
//		List<Adress> adresses = this.adressRepository.findAll() ;
//		
//		List<Phone> phones = this.phoneRepository.findAll() ;		
//		
//		Stream.of( "ORIMA Kervens" , "Vilsaint Vansley Jean Renerlens" , "Mondesir Marckender" , "Jean John Wesley" , "ORIMA Reginald" , "Gerome Smandina").forEach( (name) -> {
//	
//			employeeRepository.save( new Employee(null, UUID.randomUUID().toString() , name, name, Math.random() * 10D + 4000 , false, "CDD" , new Date() , "Actif" , adresses , phones )) ;
//		
//		});		
		
		
	}

}
