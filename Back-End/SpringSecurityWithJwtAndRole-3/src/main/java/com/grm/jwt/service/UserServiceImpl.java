package com.grm.jwt.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grm.jwt.config.SecurityConstants;
import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.exception.RoleException;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.RoleRepo;
import com.grm.jwt.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> getAllUsersByRole(String role) {
		role = role.toUpperCase();

		List<User> list = userRepo.getUsersByRoleName("ROLE_" + role);

		if (list.size() == 0)
			throw new RoleException("No user present with this role");

		return list;
	}

	@Override
	public String setRoleToUserById(String role, Long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new UserException("User not present with id"));

		Role r = Role.getRoleByName(role);

		user.getRoles().add(r);

		roleRepo.save(r);

		user = userRepo.save(user);

		return r.getRoleName().toString() + "added successfully";
	}

	@Override
	public User setJwtToUserByName(User user, String jwt) {

		user.setJwt(jwt);
		user.setJwtGeneratedTimestamp(LocalDateTime.now());
		user.setJwtValidTill(LocalDateTime.now().plus(Duration.ofMillis(SecurityConstants.JWT_VALID_TILL)));

		return userRepo.save(user);
	}

	@Override
	public List<ChannelDto> getAllChannelsByUserId(Long uId) {

		User user = userRepo.findById(uId).orElseThrow(() -> new UserException("User not present with id"));

		return user.getChannels().stream().map(c -> c.getChannelDtoByEntity(c)).collect(Collectors.toList());

	}

	@Override
	public List<WorkspaceDto> getWorkspaceByUserId(Long uId) {
		
		User user = userRepo.findById(uId).orElseThrow(() -> new UserException("User not present with id"));
		
		return user.getWorkspaces()
				                   .stream()
				                   .map(w -> w.getWorkspaceDto(w))
				                   .collect(Collectors.toList());
	}

}
