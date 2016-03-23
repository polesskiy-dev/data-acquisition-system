package com.polesskiy.service.user;

import com.polesskiy.entity.User;

import java.util.List;

/**
 * Created by polesskiy on 23.03.16.
 */
public interface UserService {
    User findByLogin(String login);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByLogin(String login);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);
}
