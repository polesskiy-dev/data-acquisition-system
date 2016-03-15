package com.polesskiy.dao.sensor;

import com.polesskiy.entity.Sensor;

import java.util.List;

/**
 * Created by polesskiy on 11.03.16.
 */
public interface SensorDAOI {
    Sensor add(Sensor sensor);
    void update(Sensor sensor);
    void deleteById(int id);
    Sensor getById(int id);
    List<Sensor> getAllSensors();
}
