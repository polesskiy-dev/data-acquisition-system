package com.polesskiy.service.sensordata;

import com.polesskiy.dao.sensordata.SensorDataDAO;
import com.polesskiy.dao.sensordata.SensorDataDAOImp;
import com.polesskiy.entity.SensorData;
import com.polesskiy.service.GenericServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SensorData Service layer
 */
@Service("sensorDataService")
@Transactional
public class SensorDataServiceImp extends GenericServiceImp<SensorData, Integer> implements SensorDataService {
    static SensorDataDAO sensorDataDAO = new SensorDataDAOImp();

    public SensorDataServiceImp() {
        super(sensorDataDAO);
    }

    @Override
    public void saveSensorData(SensorData sensorData) {
        super.save(sensorData);
    }
}
