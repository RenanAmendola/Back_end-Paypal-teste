package com.project_paypal.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_paypal.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{



    public List<User> findByEmailContainingIgnoreCase(String email);

    public Optional<User> findByEmail(String email);


    public List<User> findByName(String name);

}
