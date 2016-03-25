package com.polesskiy.dao.sensor;

import com.polesskiy.dao.GenericDAOImp;
import com.polesskiy.entity.Sensor;
import org.springframework.stereotype.Repository;

/**
 * Created by polesskiy on 24.03.16.
 */
@Repository
public class SensorDAOImp extends GenericDAOImp<Sensor, String> implements SensorDAO {
    public SensorDAOImp() {
        super(Sensor.class);
    }
}
