package com.project_paypal.project.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project_paypal.project.model.product;
import com.project_paypal.project.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	

	@Autowired
	private ProductRepository ProductRE;
	

	@GetMapping("/")
	public ResponseEntity<List<product>> GetAll(){
		
		return ResponseEntity.ok(ProductRE.findAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<product> getById(@PathVariable Long id){
		return ProductRE.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
	
	@PostMapping
	public ResponseEntity<product> post(@Valid @RequestBody product product){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ProductRE.save(product));
		
}
	
	@PutMapping
	public ResponseEntity<product> put(@Valid @RequestBody product product){
		return ProductRE.findById(product.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(ProductRE.save(product)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
		Optional<product> product = ProductRE.findById(id);
		
			if(product.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			ProductRE.deleteById(id);
	}

}
