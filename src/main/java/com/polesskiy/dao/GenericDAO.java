package com.polesskiy.dao;

import java.io.Serializable;

/**
 * Genetic CRUD DAO
 *
 * @param <T>  Type of object to persist
 * @param <PK> Primary yey to find by in database
 */
public interface GenericDAO<T, PK extends Serializable> {
    public T save(T entity);

    public Boolean delete(T entity);

    public T edit(T entuity);

    public T find(PK entityIdentifier);
}
