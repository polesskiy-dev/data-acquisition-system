package com.polesskiy.service.sensor;

import com.polesskiy.dao.sensor.SensorDAO;
import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.entity.Sensor;
import com.polesskiy.service.EMF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 * Sensor service implementation
 */
@Service("sensorService")
@Transactional
public class SensorServiceImp implements SensorService {
//    @Autowired
    private SensorDAO sensorDAO = new SensorDAOImp();

    @Override
    public Sensor findSensor(String name) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor sensor = sensorDAO.find(name);
            entityManager.getTransaction().commit();
            return sensor;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void saveSensor(Sensor sensor) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            sensorDAO.save(sensor);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.printf("Maybe %s: %s already exists in database\r\n", sensor.getClass(), sensor);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Sensor editSensor(Sensor sensor) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor updatedSensor = sensorDAO.edit(sensor);
            entityManager.getTransaction().commit();
            return updatedSensor;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean deleteSensor(String name) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor sensor = sensorDAO.find(name);
            Boolean result = sensorDAO.delete(sensor);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean isSensorExists(Sensor sensor){
        return this.findSensor(sensor.getName()) != null;
    }
}
