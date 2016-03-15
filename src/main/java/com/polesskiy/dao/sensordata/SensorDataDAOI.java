package com.polesskiy.dao.sensordata;

import com.polesskiy.entity.SensorData;
import java.util.List;

/**
 * Created by polesskiy on 13.03.16.
 */
public interface SensorDataDAOI {
    SensorData add(SensorData sensorData);
    List<SensorData> getAllSensorsData();
}
