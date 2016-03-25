package com.polesskiy.fasade.dao.sensor;

import com.polesskiy.entity.Sensor;
import com.polesskiy.fasade.dao.GenericDAOImp;
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
