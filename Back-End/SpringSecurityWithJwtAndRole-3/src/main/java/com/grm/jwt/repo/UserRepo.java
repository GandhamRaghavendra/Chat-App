package com.grm.jwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grm.jwt.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);

}
