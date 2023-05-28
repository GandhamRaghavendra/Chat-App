package com.grm.jwt.Dtos;

import com.grm.jwt.model.Role;
import com.grm.jwt.model.RoleName;
import com.grm.jwt.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
	private String name;
	private String pass;
	private String role;

	public User getUser() {
		User user = new User();

		user.setUsername(name);
		user.setPassword(pass);

		Role r = new Role();

		try {
			if (role.equals("admin")) {
				r.setRoleName(RoleName.ROLE_ADMIN);
			} 
			else if (role.equals("moderate")) {
				r.setRoleName(RoleName.ROLE_MODERATOR);
			} 
			else
				r.setRoleName(RoleName.ROLE_USER);
		} 
		catch (NullPointerException e) {
			r.setRoleName(RoleName.ROLE_USER);
		}
		
		user.getRoles().add(r);

		return user;
	}
}
