package com.grm.jwt.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grm.jwt.Dtos.UserResponseDto;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.RoleRepo;
import com.grm.jwt.repo.UserRepo;

@RestController
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getUsers() {
		// Implement your logic to retrieve and return users
		List<User> list = userRepo.findAll();
		
		List<UserResponseDto> res = list.stream().map(user -> UserResponseDto.getUserRegiserDtoFromUser(user)).collect(Collectors.toList());

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PostMapping("/addRole/{id}/{role}")
	public ResponseEntity<User> addRoleById(@PathVariable Long id, @PathVariable String role) {
		User user = userRepo.findById(id).orElseThrow(() -> new UserException("User not present with id"));

		Role r = Role.getRoleByName(role);

		user.getRoles().add(r);

		roleRepo.save(r);

		user = userRepo.save(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
