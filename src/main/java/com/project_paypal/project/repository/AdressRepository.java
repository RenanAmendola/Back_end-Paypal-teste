package com.project_paypal.project.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_paypal.project.model.adress;

@Repository
public interface AdressRepository extends JpaRepository<adress, Long>{

	Optional<adress> findByUserId(Long userId);
	
	
}
