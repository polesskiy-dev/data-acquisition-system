package com.polesskiy.dao.sensor;

import com.polesskiy.dao.GenericDAO;
import com.polesskiy.entity.Sensor;

/**
 * Sensor DAO
 */
public interface SensorDAO extends GenericDAO<Sensor, Long> {
    Sensor find(String userLogin, long id);
}
