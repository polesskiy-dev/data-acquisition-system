package com.polesskiy.fasade.dao.user;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.GenericDAO;

import java.util.Collection;

/**
 * User DAO
 */

public interface UserDAO extends GenericDAO<User, String> {
    Collection<User> getAllUsers();
}
