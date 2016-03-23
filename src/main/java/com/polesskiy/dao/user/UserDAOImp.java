package com.polesskiy.dao.user;

import com.polesskiy.dao.EMFService;
import com.polesskiy.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by polesskiy on 11.03.16.
 */
public class UserDAOImp implements UserServiceDAOI {

    @Override
    public boolean add(User user) {
        if (user != null) {
            EntityManager entityManager = EMFService.get().createEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(user);
                entityManager.getTransaction().commit();
                return true;
            } finally {
                entityManager.close();
            }
        } else
            return false;

    }

    @Override
    public boolean deleteByLogin(String login) {
        if (login != null) {
            EntityManager entityManager = EMFService.get().createEntityManager();
            try {
                entityManager.getTransaction().begin();
                User userForDeleting = entityManager.find(User.class, login);
                entityManager.remove(userForDeleting);
                entityManager.getTransaction().commit();
                return true;
            } finally {
                entityManager.close();
            }
        } else return false;
    }

    @Override
    public User getByLogin(String login) {
        if (login != null) {
            EntityManager entityManager = EMFService.get().createEntityManager();
            try {
                return entityManager.find(User.class, login);
            } finally {
                entityManager.close();
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = EMFService.get().createEntityManager();
        try {
            TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
            return namedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }

    /*
        public void update(Matrix matrix){
        entityManager.getTransaction().begin();
        entityManager.merge(matrix);
        entityManager.getTransaction().commit();
    }*/

}
