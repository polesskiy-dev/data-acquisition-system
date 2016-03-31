package com.polesskiy.service;

import java.io.Serializable;

/**
 * Generic Service interface
 *
 * @param <T>  entity to persist
 * @param <PK> primary key of entity
 */
public interface GenericService<T, PK extends Serializable> {
    T find(PK key);

    void save(T entity);

    T edit(T entity);

    Boolean delete(PK key);
}
