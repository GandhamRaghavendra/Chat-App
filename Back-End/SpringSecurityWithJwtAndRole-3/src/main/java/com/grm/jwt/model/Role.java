package com.grm.jwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "name")
  private RoleName roleName;
  
  public static Role getRoleByName(String roleName) {
	  Role role = new Role();
	  
	  if(roleName.equals("admin")) {
		  role.setRoleName(RoleName.ROLE_ADMIN);
	  }
	  else role.setRoleName(RoleName.ROLE_MODERATOR);
	  
	  return role;
  }
  
  // Constructors, getters, and setters
}
