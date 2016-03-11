package com.polesskiy.service.user;

import com.polesskiy.entity.User;
import com.polesskiy.service.EMFService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by polesskiy on 11.03.16.
 */
public class UserServiceImp implements UserServiceI {
    public EntityManager entityManager = EMFService.get().createEntityManager();

    @Override
    public boolean add(User user) {
        if (user != null) {
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
    public void deleteByLogin(String login) {
        if (login != null) {
            //User userForDeleting = getByLogin(login);
            try {
                entityManager.getTransaction().begin();
                User userForDeleting = entityManager.find(User.class, login);
                entityManager.remove(userForDeleting);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        }
    }

    @Override
    public User getByLogin(String login) {
        if (login != null) {
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
