package com.polesskiy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by polesskiy on 11.03.16.
 */

@Entity
@Table(name = "SENSORS")
@NamedQueries({
        @NamedQuery(name = "Sensor.getAll", query = "SELECT c from Sensor c ORDER BY c.id DESC"),
})
public class Sensor implements Serializable {
    @Id
    @Column(name = "SENSOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_LOGIN", nullable = false)
    private User ownerUser;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INFO")
    private String additionalInfo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ownerSensor")
    private Set<SensorData> sensorDataList;

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public Set<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(Set<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    @Override
    public String toString() {
        String serializedSensor = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedSensor = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serializedSensor;
    }
}
