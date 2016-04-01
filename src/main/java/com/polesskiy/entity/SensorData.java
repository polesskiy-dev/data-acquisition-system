package com.polesskiy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by polesskiy on 11.03.16.
 */
@Entity
@Table(name = "SENSORS_DATA")
@NamedQueries({
        @NamedQuery(name = "SensorData.getAll", query = "SELECT c from SensorData c ORDER BY c.id DESC"),
})
public class SensorData implements Serializable {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SENSOR_ID", nullable = false)
    private Sensor ownerSensor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE")
    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "FIELD")
    @CollectionTable(name = "SENSORS_DATA_MAP", joinColumns = @JoinColumn(name = "ID"))
    @Column(name = "VALUE")
    private Map<String, String> data = new HashMap<String, String>();

    public SensorData() {
    }

    public SensorData(Sensor ownerSensor, Date date, Map<String, String> data) {
        this.ownerSensor = ownerSensor;
        this.date = date;
        this.data = data;
    }

    //<editor-fold desc="setters ans getters">
    public long getId() {
        return id;
    }

    public Sensor getOwnerSensor() {
        return ownerSensor;
    }

    public void setOwnerSensor(Sensor ownerSensor) {
        this.ownerSensor = ownerSensor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    //</editor-fold>

    @Override
    public String toString() {
        String serializedSensorData = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            serializedSensorData = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serializedSensorData;
    }
}
