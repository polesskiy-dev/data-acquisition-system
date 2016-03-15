package com.polesskiy.dao.user;

import com.polesskiy.entity.User;
import java.util.List;

/**
 * Created by polesskiy on 11.03.16.
 */
interface UserServiceDAOI {
    boolean add(User user);
    void deleteByLogin(String login);
    User getByLogin(String login);
    List<User> getAllUsers();
}
