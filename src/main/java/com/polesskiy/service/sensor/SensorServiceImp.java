package com.polesskiy.service.sensor;

import com.polesskiy.dao.sensor.SensorDAO;
import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.entity.Sensor;
import com.polesskiy.service.EMF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public Sensor findSensor(long id) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor sensor = sensorDAO.find(id);
            entityManager.getTransaction().commit();
            return sensor;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Sensor findSensor(String userLogin, long id) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor sensor = sensorDAO.find(userLogin, id);
            entityManager.getTransaction().commit();
            return sensor;
        } catch (NoResultException e) {
            System.err.printf("NoResultException: no sensor with id %d for user %s\r\n", id, userLogin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            sensorDAO.save(sensor);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return sensor;
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
    public Boolean deleteSensor(long id) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            Sensor sensor = sensorDAO.find(id);
            Boolean result = sensorDAO.delete(sensor);
            entityManager.getTransaction().commit();
            return result;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean isSensorExists(Sensor sensor) {
        return this.findSensor(sensor.getId()) != null;
    }
}
