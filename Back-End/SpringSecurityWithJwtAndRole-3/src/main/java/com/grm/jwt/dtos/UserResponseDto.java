package com.grm.jwt.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;

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
public class UserResponseDto {
	private Long id;
	private String name;
	private String jwt;
	private LocalDateTime jwtCreatedTime;
	private LocalDateTime jwtValidTill;
	private Set<Role> roles = new HashSet<>();
	
	public static UserResponseDto getUserResponseDtoFromUser(User user) {
		UserResponseDto res = UserResponseDto.builder()
				                 .id(user.getId())
		                         .name(user.getUsername())
		                         .jwt(user.getJwt())
		                         .jwtCreatedTime(user.getJwtGeneratedTimestamp())
		                         .jwtValidTill(user.getJwtValidTill())
		                         .roles(user.getRoles())
		                         .build();
		
		return res;
	}
}
