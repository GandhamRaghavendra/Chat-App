package com.grm.jwt.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grm.jwt.Dtos.UserRegisterDto;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.RoleRepo;
import com.grm.jwt.repo.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}

	@PostMapping
	public ResponseEntity<User> saveCustomerHandler(@RequestBody UserRegisterDto dto) {

		User user = dto.getUser();

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<Role> roleSet = user.getRoles();

		for (Role r : roleSet) {
			roleRepo.save(r);
		}

		User registeredUser = userRepo.save(user);

		return new ResponseEntity<>(registeredUser, HttpStatus.ACCEPTED);

	}
}
