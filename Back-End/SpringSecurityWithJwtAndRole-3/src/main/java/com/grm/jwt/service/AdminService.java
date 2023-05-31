package com.grm.jwt.service;

import java.util.List;

import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.model.User;

public interface AdminService {
	
	List<User> getAllUsers();
	
	List<User> getAllUsersByRole(String role);
	
	String setRoleToUserById(String role,Long id);
}
