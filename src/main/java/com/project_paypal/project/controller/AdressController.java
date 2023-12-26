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

import com.project_paypal.project.model.User;
import com.project_paypal.project.model.adress;
import com.project_paypal.project.repository.AdressRepository;
import com.project_paypal.project.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Adress")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdressController {
	
	@Autowired
	private AdressRepository adress_repository;
	
    @Autowired
    private UserRepository userRepository;


	@GetMapping("/all")
	public ResponseEntity<List<adress>> getAll(){
		return ResponseEntity.ok(adress_repository.findAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<adress> getById(@PathVariable long id){
		return adress_repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	

	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<adress>> getByUser(@PathVariable Long id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isPresent()) {
	        Optional<adress> addresses = adress_repository.findByUserId(id);
	        return ResponseEntity.ok(addresses);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
	    }
	}
	

	@PostMapping
	public ResponseEntity<adress> post(@Valid @RequestBody adress adress){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(adress_repository.save(adress));
		
	}
	
	@PutMapping
	public ResponseEntity<adress> put(@Valid @RequestBody adress adress){
		return adress_repository.findById(adress.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(adress_repository.save(adress)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
		Optional<adress> adress = adress_repository.findById(id);
		
			if(adress.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			adress_repository.deleteById(id);
	}

}
