package com.polesskiy.dao.user;

import com.polesskiy.dao.GenericDAO;
import com.polesskiy.entity.User;

import java.util.Collection;

/**
 * User DAO
 */

public interface UserDAO extends GenericDAO<User, String> {
    Collection<User> getAllUsers();
}
