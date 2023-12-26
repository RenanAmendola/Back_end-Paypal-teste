package com.project_paypal.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_paypal.project.model.product;

@Repository
public interface ProductRepository extends JpaRepository <product, Long>{

	
     public List <product>findAllByName(String name);

     public List <product>findAllByDescription(String description);

}

