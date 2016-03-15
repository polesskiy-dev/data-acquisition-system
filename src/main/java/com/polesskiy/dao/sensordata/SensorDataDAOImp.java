package com.polesskiy.dao.sensordata;

import com.polesskiy.entity.SensorData;
import com.polesskiy.dao.EMFService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by polesskiy on 13.03.16.
 */
public class SensorDataDAOImp implements SensorDataDAOI {
    @Override
    public SensorData add(SensorData sensorData) {
        if (sensorData != null) {
            EntityManager entityManager = EMFService.get().createEntityManager();
            try {
                entityManager.getTransaction().begin();
                SensorData updatedSensorData = entityManager.merge(sensorData);
                entityManager.getTransaction().commit();
                return updatedSensorData;
            } finally {
                entityManager.close();
            }
        } else
            return null;
    }

    @Override
    public List<SensorData> getAllSensorsData() {
        EntityManager entityManager = EMFService.get().createEntityManager();
        try {
            TypedQuery<SensorData> namedQuery = entityManager.createNamedQuery("SensorData.getAll", SensorData.class);
            return namedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
