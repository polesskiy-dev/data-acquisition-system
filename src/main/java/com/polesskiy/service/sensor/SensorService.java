package com.polesskiy.service.sensor;

import com.polesskiy.entity.Sensor;

import java.util.Collection;

/**
 * Created by polesskiy on 25.03.16.
 */
public interface SensorService {
    Sensor findSensor(String name);

    Sensor saveSensor(Sensor sensor);

    Sensor editSensoe(Sensor sensor);

    Boolean deleteSensor(String name);

    Collection<Sensor> getAllSensors();
}
