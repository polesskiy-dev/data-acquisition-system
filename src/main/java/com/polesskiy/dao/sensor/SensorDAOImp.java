package com.polesskiy.dao.sensor;

import com.polesskiy.dao.GenericDAOImp;
import com.polesskiy.entity.Sensor;
import org.springframework.stereotype.Repository;

/**
 * Sensor DAO implementation
 */
@Repository("sensorDAO")
public class SensorDAOImp extends GenericDAOImp<Sensor, Long> implements SensorDAO {
    public SensorDAOImp() {
        super(Sensor.class);
    }
}
