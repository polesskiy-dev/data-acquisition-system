package com.polesskiy.service.sensor;

import com.polesskiy.entity.Sensor;

/**
 * Sensor service
 */
public interface SensorService {
    Sensor findSensor(String name);

    void saveSensor(Sensor sensor);

    Sensor editSensor(Sensor sensor);

    Boolean deleteSensor(String name);

    Boolean isSensorExists(Sensor sensor);
}
