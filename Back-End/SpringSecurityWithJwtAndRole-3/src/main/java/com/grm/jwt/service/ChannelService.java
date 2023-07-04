package com.grm.jwt.service;

import java.util.List;

import com.grm.jwt.dtos.ChannelDto;
import com.grm.jwt.dtos.UserResponseDto;

public interface ChannelService {

	ChannelDto createChannel(ChannelDto dto, Long wId);
	
	ChannelDto updateChannel(ChannelDto dto);
	
	String removeChannelFromWorkspace(Long cId);
	
	String addMemberTOChannel(Long cId, Long uId);
	
	String removeMemberFromChannel(Long cId, Long uId);
	
	List<UserResponseDto> getAllMembersOfChannel(Long cId);
}
