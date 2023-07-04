package com.grm.jwt.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.UserRegisterDto;
import com.grm.jwt.dtos.UserResponseDto;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.RoleRepo;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}

	@PostMapping
	public ResponseEntity<UserResponseDto> saveCustomerHandler(@RequestBody UserRegisterDto dto) {

		User user = dto.getUser();

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<Role> roleSet = user.getRoles();

		for (Role r : roleSet) {
			roleRepo.save(r);
		}

		User registeredUser = userRepo.save(user);
		
		UserResponseDto res = UserResponseDto.getUserResponseDtoFromUser(registeredUser);

		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

	}
	
	@GetMapping("/channels")
	public ResponseEntity<List<ChannelDto>> getAllChannnelsByUserId(@PathParam("uId") Long uId){
		
		List<ChannelDto> list = userService.getAllChannelsByUserId(uId);
		
		return new ResponseEntity<List<ChannelDto>>(list,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/workspace")
	public ResponseEntity<List<WorkspaceDto>> getAllWorkspaceByUserId(@PathParam("uId") Long uId){
		
		List<WorkspaceDto> list = userService.getWorkspaceByUserId(uId);
		
		return new ResponseEntity<List<WorkspaceDto>>(list,HttpStatus.ACCEPTED);
	}
}
