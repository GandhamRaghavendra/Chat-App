package com.grm.jwt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grm.jwt.Dtos.UserResponseDto;
import com.grm.jwt.config.SecurityConstants;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;

	@GetMapping("/signIn")
	public ResponseEntity<UserResponseDto> getLoggedInCustomerDetailsHandler(Authentication auth,HttpServletResponse response) {
		
		String jwt= response.getHeader(SecurityConstants.JWT_HEADER);

		User user = userRepo.findByUsername(auth.getName())
				.orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));

		// to get the token in body, pass HttpServletResponse inside this method
		// parameter
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		
		user = userService.setJwtToUserByName(user, jwt);
		
		UserResponseDto res = UserResponseDto.getUserRegiserDtoFromUser(user);

		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

}
