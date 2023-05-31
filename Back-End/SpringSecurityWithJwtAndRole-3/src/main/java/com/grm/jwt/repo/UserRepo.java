package com.grm.jwt.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grm.jwt.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query(value = "SELECT u.* " + 
	               "FROM users u " + 
			       "INNER JOIN user_roles ur ON u.id = ur.user_id " +
			       "INNER JOIN roles r ON ur.role_id = r.id " + 
			       "WHERE r.name = :roleName", nativeQuery = true)
	List<User> getUsersByRoleName(String roleName);

}
