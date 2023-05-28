package com.grm.jwt.service;

import com.grm.jwt.model.User;

public interface UserService {

    User setJwtToUserByName(User user,String jwt);
}
