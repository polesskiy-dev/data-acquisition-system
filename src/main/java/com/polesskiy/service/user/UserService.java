package com.polesskiy.service.user;

import com.polesskiy.entity.User;

import java.util.Collection;

/**
 * Created by polesskiy on 23.03.16.
 */
public interface UserService {
    User findUser(String login);

    void saveUser(User user);

    User editUser(User user);

    Boolean deleteUser(String login);

    Collection<User> getAllUsers();

    void deleteAllUsers();

    boolean isUserExists(User user);
}
