 package com.project_paypal.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project_paypal.project.model.User;
import com.project_paypal.project.model.UserLogin;
import com.project_paypal.project.repository.UserRepository;
import com.project_paypal.project.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService UserService;
		
	@Autowired
	private UserRepository UsuRe;


	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll(){
		return ResponseEntity.ok(UsuRe.findAll());
	}


	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable long id){
		return UsuRe.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/Login")
	public ResponseEntity<UserLogin> autenticationUsuario(@RequestBody Optional<UserLogin> user){
		
		return UserService.log_inUser(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
	}
		
	@PostMapping("/Sing_up")
	public ResponseEntity<User> postUsuario(@Valid @RequestBody User user){
		return UserService.registerUser(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
		
	@PutMapping("/Update")
	public ResponseEntity<User> putUsuario(@Valid @RequestBody User user){
		return UserService.updateUser(user)
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	

}
