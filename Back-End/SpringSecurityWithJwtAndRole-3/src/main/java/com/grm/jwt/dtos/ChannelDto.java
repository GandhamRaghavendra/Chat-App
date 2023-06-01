package com.grm.jwt.dtos;

import com.grm.jwt.model.Channel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {

	private Long id;

	private String name;

	private String description;
	
	private boolean isPrivate;
	
	public Channel getChannelEntity(ChannelDto dto) {
		Channel res = Channel.builder()
		                 .name(dto.getName())
		                 .description(dto.getDescription())
		                 .isPrivate(dto.isPrivate())
		                 .build();
		
		return res;
	}
}
