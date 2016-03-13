package com.polesskiy.service.sensor;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.service.EMFService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class SensorServiceImp implements SensorServiceI {


    @Override
    public Sensor add(Sensor sensor) {
        if (sensor != null) {
            EntityManager entityManager = EMFService.get().createEntityManager();
            try {
                entityManager.getTransaction().begin();
                Sensor updatedSensor = entityManager.merge(sensor);
                entityManager.getTransaction().commit();
                return updatedSensor;
            } finally {
                entityManager.close();
            }
        } else
            return null;
    }

    @Override
    public void update(Sensor sensor) {
//TODO implement this
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = EMFService.get().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Sensor sensorForDeleting = entityManager.find(Sensor.class, id);
            entityManager.remove(sensorForDeleting);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Sensor getById(int id) {
        EntityManager entityManager = EMFService.get().createEntityManager();
        try {
            return entityManager.find(Sensor.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Sensor> getAllSensors() {
        EntityManager entityManager = EMFService.get().createEntityManager();
        try {
            TypedQuery<Sensor> namedQuery = entityManager.createNamedQuery("Sensor.getAll", Sensor.class);
            return namedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
