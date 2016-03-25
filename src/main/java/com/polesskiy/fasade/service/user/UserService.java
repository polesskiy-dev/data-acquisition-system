package com.polesskiy.fasade.service.user;

import com.polesskiy.entity.User;

import java.util.Collection;

/**
 * Created by polesskiy on 25.03.16.
 */
public interface UserService {
    User saveUser(User user);

    Boolean deleteUser(String login);

    User editUser(User user);

    User findUser(String login);

    Collection<User> getAllUsers();
}
