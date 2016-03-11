package com.polesskiy.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by polesskiy on 11.03.16.
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "SELECT c from User c ORDER BY c.id DESC"),
})
public class User implements Serializable {
    @Id
    private String login;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        String serializedUser = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedUser = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serializedUser;
    }
}
