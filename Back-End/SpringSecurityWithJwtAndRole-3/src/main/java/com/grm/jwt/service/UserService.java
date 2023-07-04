package com.grm.jwt.service;

import java.util.List;

import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.model.User;

public interface UserService {

	List<User> getAllUsers();
	
	List<User> getAllUsersByRole(String role);
	
	String setRoleToUserById(String role,Long id);
	
    User setJwtToUserByName(User user,String jwt);
    
    List<ChannelDto> getAllChannelsByUserId(Long uId);
    
    List<WorkspaceDto> getWorkspaceByUserId(Long uId);
}
