package com.polesskiy.fasade.service.user.plainimplementation;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.user.UserDAOImp;
import com.polesskiy.fasade.service.EMF;
import com.polesskiy.fasade.service.user.UserService;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * User Service layer
 * must be refactored to reduce code duplication
 */
public class UserServiceImp implements UserService {
    private UserDAOImp userDAOImp = new UserDAOImp();
    private EntityManager entityManager;

    @Override
    public User saveUser(User user) {
        entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User resultUser = userDAOImp.save(user);
            entityManager.getTransaction().commit();
            return resultUser;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean deleteUser(String login) {
        entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = this.findUser(login);
            Boolean result = userDAOImp.delete(user);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User editUser(User user) {
        entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User resultUser = userDAOImp.edit(user);
            entityManager.getTransaction().commit();
            return resultUser;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User findUser(String login) {
        entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = userDAOImp.find(login);
            if (user != null) {
            }
            ;
            entityManager.getTransaction().commit();
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<User> getAllUsers() {
        entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Collection<User> users = userDAOImp.getAllUsers();
            entityManager.getTransaction().commit();
            return users;
        } finally {
            entityManager.close();
        }
    }
}
