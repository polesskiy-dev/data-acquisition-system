package com.polesskiy.service.sensor;

import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.entity.Sensor;
import com.polesskiy.service.GenericServiceImp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sensor service
 */
@Service("sensorService")
@Transactional
public class SensorServiceImp extends GenericServiceImp<Sensor, String> implements SensorService {
    static SensorDAOImp sensorDAO = new SensorDAOImp();

    public SensorServiceImp() {
        super(sensorDAO);
    }

    @Override
    public Sensor findSensor(String name) {
        return super.find(name);
    }

    @Override
    public void saveSensor(Sensor sensor) {
        super.save(sensor);
    }

    @Override
    public Sensor editSensor(Sensor sensor) {
        return super.edit(sensor);
    }

    @Override
    public Boolean deleteSensor(String name) {
        return super.delete(name);
    }

    @Override
    public Boolean isSensorExists(Sensor sensor){
        return this.findSensor(sensor.getName()) != null;
    }
}
