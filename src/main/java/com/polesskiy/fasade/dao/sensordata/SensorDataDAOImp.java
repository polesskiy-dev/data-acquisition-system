package com.polesskiy.fasade.dao.sensordata;

import com.polesskiy.entity.SensorData;
import com.polesskiy.fasade.dao.GenericDAOImp;
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
