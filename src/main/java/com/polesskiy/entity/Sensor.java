package com.polesskiy.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;

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
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INFO")
    private String additionalInfo;

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
