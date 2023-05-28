package com.grm.jwt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.grm.jwt.model.Role;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.UserRepo;


@Service
public class CustomerUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<User> opt= userRepo.findByUsername(username);

		if(opt.isPresent()) {
			
			User user= opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			
			Set<Role> roles = user.getRoles();
			
			for(Role r:roles) {
				SimpleGrantedAuthority sga= new SimpleGrantedAuthority(r.getRoleName().toString());
				authorities.add(sga);				
			}
			
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);	
			
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
	}

}
