package com.polesskiy.fasade.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public Boolean delete(T entity) {
        try {
            entityManager.remove(entity);
        } catch (Exception ex) {
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
