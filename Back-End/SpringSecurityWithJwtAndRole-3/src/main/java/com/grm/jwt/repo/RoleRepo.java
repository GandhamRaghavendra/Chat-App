package com.grm.jwt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grm.jwt.model.Role;
import com.grm.jwt.model.RoleName;
public interface RoleRepo extends JpaRepository<Role, Long>{

	List<Role> findByRoleName(RoleName roleName);
}
