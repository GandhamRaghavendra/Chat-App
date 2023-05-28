package com.grm.jwt.service;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grm.jwt.config.SecurityConstants;
import com.grm.jwt.model.User;
import com.grm.jwt.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public User setJwtToUserByName(User user, String jwt) {
		
		user.setJwt(jwt);
		user.setJwtGeneratedTimestamp(LocalDateTime.now());
		user.setJwtValidTill(LocalDateTime.now().plus(Duration.ofMillis(SecurityConstants.JWT_VALID_TILL)));

		return userRepo.save(user);
	}

}
