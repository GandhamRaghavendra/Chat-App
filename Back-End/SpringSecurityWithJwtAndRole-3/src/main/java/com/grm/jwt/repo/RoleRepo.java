package com.grm.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grm.jwt.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

}
