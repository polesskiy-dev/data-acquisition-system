package com.polesskiy.service.user;

import com.polesskiy.dao.user.UserDAO;
import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.User;
import com.polesskiy.service.EMF;
import com.polesskiy.service.GenericServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;

/**
 * User Service layer
 */
@Service("userService")
@Transactional
public class UserServiceImp extends GenericServiceImp<User, String> implements UserService {
    static final UserDAO userDAO = new UserDAOImp();

    public UserServiceImp() {
        super(userDAO);
    }

    /**
     * Save user to DB
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        super.save(user);
    }

    /**
     * Delete user by login from DB
     *
     * @param login
     * @return result of deleting
     */
    @Override
    public Boolean deleteUser(String login) {
        return super.delete(login);
    }

    /**
     * Update user in DB
     *
     * @param user
     * @return
     */
    @Override
    public User editUser(User user) {
        return super.edit(user);
    }

    /**
     * Find user by login
     *
     * @param login
     * @return
     */
    @Override
    public User findUser(String login) {
        return super.find(login);
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
