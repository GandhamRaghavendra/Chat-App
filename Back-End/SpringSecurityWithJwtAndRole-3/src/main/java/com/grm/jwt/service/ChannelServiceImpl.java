package com.grm.jwt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.UserResponseDto;
import com.grm.jwt.exception.ChannelException;
import com.grm.jwt.exception.UserException;
import com.grm.jwt.exception.WorkspaceException;
import com.grm.jwt.model.Channel;
import com.grm.jwt.model.User;
import com.grm.jwt.model.Workspace;
import com.grm.jwt.repo.ChannelRepo;
import com.grm.jwt.repo.UserRepo;
import com.grm.jwt.repo.WorkspaceRepo;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	private ChannelRepo channelRepo;

	@Autowired
	private WorkspaceRepo workspaceRepo;

	@Autowired
	private UserRepo userRepo;

	@Override
	public ChannelDto createChannel(ChannelDto dto, Long wId) {

		Workspace workspace = workspaceRepo.findById(wId)
				.orElseThrow(() -> new WorkspaceException("Invalid Workspace Id..!"));

		Channel entity = dto.getChannelEntity(dto);

		entity.setWorkspace(workspace);

		entity = channelRepo.save(entity);

		return entity.getChannelDtoByEntity(entity);
	}

	@Override
	public ChannelDto updateChannel(ChannelDto dto) {
		Channel cha = channelRepo.findById(dto.getId())
				.orElseThrow(() -> new ChannelException("Invalid Channel Id..!"));

		cha.setName(dto.getName());
		cha.setDescription(dto.getDescription());
		cha.setPrivate(dto.isPrivate());

		cha = channelRepo.save(cha);

		return cha.getChannelDtoByEntity(cha);
	}

	@Override
	@Transactional
	public String removeChannelFromWorkspace(Long cId) {
		Channel cha = channelRepo.findById(cId).orElseThrow(() -> new ChannelException("Invalid Channel Id..!"));

		Workspace workspace = cha.getWorkspace();

		workspace.getChannels().remove(cha);

		workspaceRepo.save(workspace);

		channelRepo.delete(cha);

		return "Channel with Id : " + cha.getId() + " removeed from workspace with name : " + workspace.getName();
	}

	@Override
	public String addMemberTOChannel(Long cId, Long uId) {

		Channel cha = channelRepo.findById(cId).orElseThrow(() -> new ChannelException("Invalid Channel Id..!"));

		User user = userRepo.findById(uId).orElseThrow(() -> new UserException("Invalid User Id..!"));
		
		if(checkMemberPresentOrNot(cha.getMembers(), user)) throw new ChannelException("Member already Exist..!");

		cha.getMembers().add(user);

		user.getChannels().add(cha);

		channelRepo.save(cha);

		return "User : " + user.getUsername() + " added to " + cha.getName() + " channel.";
	}

	@Override
	public String removeMemberFromChannel(Long cId, Long uId) {
		
		Channel cha = channelRepo.findById(cId).orElseThrow(() -> new ChannelException("Invalid Channel Id..!"));

		User user = userRepo.findById(uId).orElseThrow(() -> new UserException("Invalid User Id..!"));
		
		if(!checkMemberPresentOrNot(cha.getMembers(), user)) throw new ChannelException("Member not Exist..!");
		
		cha.getMembers().remove(user);
		
		user.getChannels().remove(cha);
		
		channelRepo.save(cha);
		
		return "User : "+user.getUsername()+" removed from "+cha.getName()+" channel.";
	}

	@Override
	public List<UserResponseDto> getAllMembersOfChannel(Long cId) {
		
		Channel cha = channelRepo.findById(cId).orElseThrow(() -> new ChannelException("Invalid Channel Id..!"));
		
		return cha.getMembers().stream()
				                        .map(u -> UserResponseDto.getUserResponseDtoFromUser(u))
				                        .collect(Collectors.toList());
	}
	
	public boolean checkMemberPresentOrNot(List<User> list, User user) {
		
		return list.contains(user);
		
	}

}
