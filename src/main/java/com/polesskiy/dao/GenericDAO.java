package com.polesskiy.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Genetic CRUD DAO
 *
 * @param <T>  Type of object to persist
 * @param <PK> Primary yey to find by in database
 */
public interface GenericDAO<T, PK extends Serializable> {
    void save(T entity);

    Boolean delete(T entity);

    T edit(T entuity);

    T find(PK entityIdentifier);

    void setEntityManager(EntityManager entityManager);
}
