package com.polesskiy.service.user;

import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.User;
import com.polesskiy.service.EMF;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * User Service layer
 * must be refactored to reduce code duplication
 */
@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
    private UserDAOImp userDAOImp = new UserDAOImp();

    @Override
    public User saveUser(User user) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User resultUser = userDAOImp.save(user);
            entityManager.getTransaction().commit();
            return resultUser;
        } catch (PersistenceException e) {
            System.err.printf("Maybe user: %s already exists in database\r\n", user);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public Boolean deleteUser(String login) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = userDAOImp.find(login);
            Boolean result = userDAOImp.delete(user);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User editUser(User user) {
        EntityManager entityManager = EMF.get().createEntityManager();
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
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = userDAOImp.find(login);
            entityManager.getTransaction().commit();
            return user;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<User> getAllUsers() {
        EntityManager entityManager = EMF.get().createEntityManager();
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

    @Override
    public void deleteAllUsers() {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAOImp.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Collection<User> users = userDAOImp.getAllUsers();
            entityManager.getTransaction().commit();
            for (User user : users) this.deleteUser(user.getLogin());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean isUserExist(User user) {
        return this.findUser(user.getLogin()) != null;
    }
}
