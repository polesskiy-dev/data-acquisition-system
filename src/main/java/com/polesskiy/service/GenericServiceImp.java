package com.polesskiy.service;

import com.polesskiy.dao.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.Serializable;

/**
 * Created by polesskiy on 28.03.16.
 */
public class GenericServiceImp<T, PK extends Serializable> implements GenericService<T, PK> {
    GenericDAO<T, PK> genericDAO;

    public GenericServiceImp(GenericDAO genericDAO) {
        this.genericDAO = genericDAO;
    }
    /**
     * Find entity in DB by primary key
     *
     * @param key primary key of entity to find
     * @return
     */
    @Override
    public T find(PK key) {
        EntityManager entityManager = EMF.get().createEntityManager();
        genericDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            T entity = genericDAO.find(key);
            entityManager.getTransaction().commit();
            return entity;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Save entity to DB
     *
     * @param entity
     */
    @Override
    public void save(T entity) {
        EntityManager entityManager = EMF.get().createEntityManager();
        genericDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            genericDAO.save(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.printf("Maybe %s: %s already exists in database\r\n", entity.getClass(), entity);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /**
     * Update entity in DB
     *
     * @param entity
     * @return updated entity
     */
    @Override
    public T edit(T entity) {
        EntityManager entityManager = EMF.get().createEntityManager();
        genericDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            T updatedEntity = genericDAO.edit(entity);
            entityManager.getTransaction().commit();
            return updatedEntity;
        } finally {
            entityManager.close();
        }
    }

    /**
     * Delete from DB by primary key
     *
     * @param key
     * @return result of deleting (successful or not)
     */
    @Override
    public Boolean delete(PK key) {
        EntityManager entityManager = EMF.get().createEntityManager();
        genericDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            T entity = genericDAO.find(key);
            Boolean result = genericDAO.delete(entity);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }
}
