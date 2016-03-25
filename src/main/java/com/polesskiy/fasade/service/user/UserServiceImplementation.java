package com.polesskiy.fasade.service.user;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by polesskiy on 25.03.16.
 */
@Service
@Transactional
public class UserServiceImplementation implements UserService {
    @Autowired
    protected UserDAO userDAO;

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public Boolean deleteUser(String login) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public User findUser(String login) {
        return null;
    }

    @Override
    public Collection<User> getAllUsers() {
        return null;
    }
}
