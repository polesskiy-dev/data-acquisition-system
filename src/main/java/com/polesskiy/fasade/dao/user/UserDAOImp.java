package com.polesskiy.fasade.dao.user;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.GenericDAOImp;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * User DAO implementation
 */
@Repository
public class UserDAOImp extends GenericDAOImp<User, String> implements UserDAO {
    public UserDAOImp() {
        super(User.class);
    }

    @Override
    public Collection<User> getAllUsers() {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
}
