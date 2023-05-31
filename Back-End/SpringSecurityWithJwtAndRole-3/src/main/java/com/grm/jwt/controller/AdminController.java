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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grm.jwt.dtos.UserResponseDto;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.service.AdminService;
import com.grm.jwt.service.WorkspaceService;
import com.grm.jwt.service.WorkspaceServiceImpl;

@RestController
@RequestMapping("/admins")
public class AdminController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private WorkspaceService workspaceService;
	
	
	@GetMapping("/all/users")
	public ResponseEntity<List<UserResponseDto>> getUsers() {
		// Implement your logic to retrieve and return users
		List<User> list = userRepo.findAll();
		
		List<UserResponseDto> res = list.stream().map(user -> UserResponseDto.getUserResponseDtoFromUser(user)).collect(Collectors.toList());

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/users/{role}")
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role){
		List<User> res = adminService.getAllUsersByRole(role);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PostMapping("/addRole/{id}/{role}")
	public ResponseEntity<String> addRoleById(@PathVariable Long id, @PathVariable String role) {
		
		String res = adminService.setRoleToUserById(role, id);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
