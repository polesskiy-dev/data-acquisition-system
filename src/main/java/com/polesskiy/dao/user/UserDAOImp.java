package com.polesskiy.dao.user;

import com.polesskiy.dao.GenericDAOImp;
import com.polesskiy.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * User DAO implementation
 */
@Repository("userDao")
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
