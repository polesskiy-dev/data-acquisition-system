package com.polesskiy.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "ownerUser", orphanRemoval=true)
    private Set<Sensor> sensors;

    public User() {
    }

    public User(String login, String password, Set<Sensor> sensors) {
        this.login = login;
        this.password = password;
        this.sensors = sensors;
    }

    //<editor-fold desc="getters and setters">
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }
    //</editor-fold>

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