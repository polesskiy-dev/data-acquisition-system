package com.polesskiy.service.user;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;

import java.util.List;

/**
 * Created by polesskiy on 11.03.16.
 */
interface UserServiceI {
    boolean add(User user);
    void deleteByLogin(String login);
    User getByLogin(String login);
    List<User> getAllUsers();
    List<Sensor> getAllSensors(User user);
}
