package com.projet.gestionemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.gestionemployee.model.Adress;


@Repository
public interface AdressRepository extends JpaRepository<Adress, Long>{

}
