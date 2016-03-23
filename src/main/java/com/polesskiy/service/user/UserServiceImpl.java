package com.polesskiy.service.user;

import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    UserDAOImp userDAOImp = new UserDAOImp();

    @Override
    public User findByLogin(String login) {
        return userDAOImp.getByLogin(login);
    }

    @Override
    public void saveUser(User user) {
        userDAOImp.add(user);
    }

    @Override
    public void updateUser(User user) {
        //TODO implements this
    }

    @Override
    public void deleteUserByLogin(String login) {
        userDAOImp.deleteByLogin(login);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAOImp.getAllUsers();
    }

    @Override
    public void deleteAllUsers() {
        for (User user : userDAOImp.getAllUsers()) userDAOImp.deleteByLogin(user.getLogin());
    }

    @Override
    public boolean isUserExist(User user) {
        return (userDAOImp.getByLogin(user.getLogin()) != null);
    }
}
