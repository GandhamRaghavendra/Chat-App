package com.grm.jwt.service;

import com.grm.jwt.dtos.ChannelDto;

public interface ChannelService {

	ChannelDto createChannel(ChannelDto dto, Long wId);
	
	ChannelDto updateChannel(ChannelDto dto);
	
	String removeChannelFromWorkspace(Long cId);
}
