package com.polesskiy.service.user;

import com.polesskiy.dao.user.UserDAO;
import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.User;
import com.polesskiy.service.EMF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Collection;

/**
 * User Service layer
 */
@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
//    @Autowired
    private UserDAO userDAO = new UserDAOImp();

    /**
     * Save user to DB
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            userDAO.save(user);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.printf("Maybe %s: %s already exists in database\r\n", user.getClass(), user);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete user by login from DB
     *
     * @param login
     * @return result of deleting
     */
    @Override
    public Boolean deleteUser(String login) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = userDAO.find(login);
            Boolean result = userDAO.delete(user);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Update user in DB
     *
     * @param user
     * @return
     */
    @Override
    public User editUser(User user) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User updatedUser = userDAO.edit(user);
            entityManager.getTransaction().commit();
            return updatedUser;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Find user by login
     *
     * @param login
     * @return
     */
    @Override
    public User findUser(String login) {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            User user = userDAO.find(login);
            entityManager.getTransaction().commit();
            return user;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Get all users from DB
     *
     * @return collection of users
     */
    @Override
    public Collection<User> getAllUsers() {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Collection<User> users = userDAO.getAllUsers();
            entityManager.getTransaction().commit();
            return users;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete all users from DB
     */
    @Override
    public void deleteAllUsers() {
        EntityManager entityManager = EMF.get().createEntityManager();
        userDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Collection<User> users = userDAO.getAllUsers();
            entityManager.getTransaction().commit();
            for (User user : users) this.deleteUser(user.getLogin());
        } finally {
            entityManager.close();
        }
    }

    /**
     * Checks whether the user exists in DB
     *
     * @param user
     * @return
     */
    @Override
    public boolean isUserExists(User user) {
        return this.findUser(user.getLogin()) != null;
    }
}
