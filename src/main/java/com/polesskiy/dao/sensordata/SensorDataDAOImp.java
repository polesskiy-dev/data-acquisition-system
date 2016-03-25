package com.polesskiy.dao.sensordata;

import com.polesskiy.dao.GenericDAOImp;
import com.polesskiy.entity.SensorData;
import org.springframework.stereotype.Repository;

/**
 * SensorData DAO implementation
 */
@Repository
public class SensorDataDAOImp extends GenericDAOImp<SensorData, Long> implements SensorDataDAO {
    public SensorDataDAOImp() {
        super(SensorData.class);
    }
}
