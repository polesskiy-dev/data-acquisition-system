package com.polesskiy.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Generic DAO CRUD implementation
 *
 * @param <T>  Type of object to persist
 * @param <PK> Primary yey to find by in database
 */
@Repository
public class GenericDAOImp<T, PK extends Serializable> implements GenericDAO<T, PK> {
    protected EntityManager entityManager;
    private Class<T> type;

    public GenericDAOImp() {
    }

    public GenericDAOImp(Class<T> type) {
        this.type = type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    //    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public Boolean delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public T edit(T entity) {
        try {
            T newEntity = entityManager.merge(entity);
            return newEntity;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public T find(PK primaryKey) {
        return (T) entityManager.find(type, primaryKey);
    }
}
