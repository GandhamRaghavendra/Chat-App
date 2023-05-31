	package com.grm.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.exception.RoleException;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.exception.WorkspaceException;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.RoleName;
import com.grm.jwt.model.User;
import com.grm.jwt.model.Workspace;
import com.grm.jwt.repo.RoleRepo;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.repo.WorkspaceRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private WorkspaceRepo workspaceRepo;
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getAllUsersByRole(String role) {
		role = role.toUpperCase();
		
		List<User> list = userRepo.getUsersByRoleName("ROLE_"+role);
		
		if(list.size() == 0) throw new RoleException("No user present with this role");
		
		return list;
	}

	@Override
	public String setRoleToUserById(String role, Long id) {
		
		User user = userRepo.findById(id).orElseThrow(() -> new UserException("User not present with id"));

		Role r = Role.getRoleByName(role);

		user.getRoles().add(r);

		roleRepo.save(r);

		user = userRepo.save(user);
		
		return r.getRoleName().toString()+"added successfully";
	}
}
