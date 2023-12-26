package com.project_paypal.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project_paypal.project.model.User;
import com.project_paypal.project.model.UserLogin;
import com.project_paypal.project.repository.UserRepository;

@Service
public class UserService {

    @Autowired
	private UserRepository UsuRe;
	
	public Optional<User> registerUser(User user){
		
		if(UsuRe.findByEmail(user.getEmail())
				.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user already exists",null);
		
		
		return Optional.of(UsuRe.save(user));										
	}
	
	public Optional<User> updateUser(User user){
		
		if(UsuRe.findById(user.getId()).isPresent()) {
			Optional<User> buscaUser = UsuRe.
					findByEmail(user.getEmail());
			if (buscaUser.isPresent()) {
				if(buscaUser.get().getId() !=user.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The user already exists", null);
			}
			
			return Optional.of(UsuRe.save(user));
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", null);
		
	}
	
	public Optional<UserLogin> log_inUser(Optional<UserLogin> UserLogin) {
		
		Optional<User> User = UsuRe.findByEmail(UserLogin.get().getEmail());
		
		if (User.isPresent()) {
			if(compararSenhas(UserLogin.get().getPassword(),User.get().getPassword())) {
				
				UserLogin.get().setId(User.get().getId());
				UserLogin.get().setName(User.get().getName());
				UserLogin.get().setLast_name(User.get().getLast_name());
				UserLogin.get().setPhone(User.get().getPhone());
				UserLogin.get().setEmail(User.get().getEmail());
				UserLogin.get().setPassword(User.get().getPassword());
				
				return UserLogin;
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is invalid", null);
	}
	

	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		return senhaDigitada.equals(senhaBanco);			
	}

}
