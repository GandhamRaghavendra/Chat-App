package com.grm.jwt.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.UserResponseDto;
import com.grm.jwt.dtos.WorkspaceDto;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.exception.WorkspaceException;
import com.grm.jwt.model.Channel;
import com.grm.jwt.model.User;
import com.grm.jwt.model.Workspace;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.repo.WorkspaceRepo;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	private WorkspaceRepo workspaceRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public WorkspaceDto createWorkspace(WorkspaceDto dto) {
		Workspace workspaceEntity = dto.getWorkspaceEntity(dto);

		Workspace savedEntity = workspaceRepo.save(workspaceEntity);

		dto.setId(savedEntity.getId());

		return dto;
	}

	@Override
	public String updateWorkspace(Long id, WorkspaceDto dto) {

		Workspace entity = workspaceRepo.findById(id).orElseThrow(() -> new WorkspaceException("Invalid Id"));

		entity.setName(dto.getName());
		entity.setDescription(dto.getDesc());

		workspaceRepo.save(entity);

		return "updated done";

	}

	@Override
	public List<WorkspaceDto> getAllWorkspace() {
		List<Workspace> list = workspaceRepo.findAll();

		return list.stream().map(ent -> ent.getWorkspaceDto(ent)).collect(Collectors.toList());
	}

	@Override
	public List<WorkspaceDto> getAllPublicWorkspace() {
		List<Workspace> list = workspaceRepo.findAll();

		return list.stream().filter(w -> !w.isPrivate()).map(w -> w.getWorkspaceDto(w)).collect(Collectors.toList());
	}

	@Override
	public String addUserToWorkspaceForAdmin(Long userId, Long worksId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserException("User not present with this id " + userId));

		Workspace workspace = workspaceRepo.findById(worksId)
				.orElseThrow(() -> new WorkspaceException("No workspace exist with this id " + worksId));
		
		if(checkUserPresentInWorkspace(workspace.getMembers(), user)) throw new WorkspaceException("User already present!");
			
		user.getWorkspaces().add(workspace);

		workspace.getMembers().add(user);

		userRepo.save(user);

		return "User with id : " + userId + " added to workspace with name : " + workspace.getName();
	}

	@Override
	public String removeUserFromWorkspaceById(Long userId, Long worksId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserException("User not present with this id " + userId));

		Workspace workspace = workspaceRepo.findById(worksId)
				.orElseThrow(() -> new WorkspaceException("No workspace exist with this id " + worksId));
		
		if(!checkUserPresentInWorkspace(workspace.getMembers(), user)) throw new WorkspaceException("User not present!");

		user.getWorkspaces().remove(workspace);
		
		workspace.getMembers().remove(user);
		
	    userRepo.save(user);
	    
	    workspaceRepo.save(workspace);
	    
	    return "User with id : " + userId + " removed from workspace with name : " + workspace.getName();
	}
	
	public boolean checkUserPresentInWorkspace(List<User> list, User user) {
		return list.contains(user);
	}

	@Override
	public List<UserResponseDto> getAllUsersByWorkspaceId(Long wId) {
		
		Workspace workspace = workspaceRepo.findById(wId)
				.orElseThrow(() -> new WorkspaceException("No workspace exist with this id " + wId));
		
		List<User> list = workspace.getMembers();
		
		
		 return list.stream()
						    .map(u -> UserResponseDto.getUserResponseDtoFromUser(u))
						    .collect(Collectors.toList());
	}

	@Override
	public List<ChannelDto> getAllChannelsByWorkspaceId(Long wId) {
		
		Workspace workspace = workspaceRepo.findById(wId)
				.orElseThrow(() -> new WorkspaceException("No workspace exist with this id " + wId));
		
		List<Channel> list = workspace.getChannels();
		
		return list.stream()
				   .map(cha -> cha.getChannelDtoByEntity(cha))
				   .collect(Collectors.toList());
	}
}
