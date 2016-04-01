package com.polesskiy.service.sensor;

import com.polesskiy.entity.Sensor;

/**
 * Sensor service
 */
public interface SensorService {
    Sensor findSensor(long id);

    Sensor findSensor(String userLogin, long id);

    Sensor saveSensor(Sensor sensor);

    Sensor editSensor(Sensor sensor);

    Boolean deleteSensor(long id);

    Boolean isSensorExists(Sensor sensor);
}
