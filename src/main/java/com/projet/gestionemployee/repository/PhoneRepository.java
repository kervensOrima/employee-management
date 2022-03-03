package com.projet.gestionemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestionemployee.model.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
