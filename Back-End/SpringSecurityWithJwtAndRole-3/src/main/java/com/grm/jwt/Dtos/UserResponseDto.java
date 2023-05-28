package com.grm.jwt.Dtos;

import java.time.LocalDateTime;

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
	private String name;
	private String jwt;
	private LocalDateTime jwtCreatedTime;
	private LocalDateTime jwtValidTill;
	
	public static UserResponseDto getUserRegiserDtoFromUser(User user) {
		UserResponseDto res = UserResponseDto.builder()
		                         .name(user.getUsername())
		                         .jwt(user.getJwt())
		                         .jwtCreatedTime(user.getJwtGeneratedTimestamp())
		                         .jwtValidTill(user.getJwtValidTill())
		                         .build();
		
		return res;
	}
}
