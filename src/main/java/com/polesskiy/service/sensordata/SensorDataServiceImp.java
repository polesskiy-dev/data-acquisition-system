package com.polesskiy.service.sensordata;

import com.polesskiy.dao.sensordata.SensorDataDAO;
import com.polesskiy.dao.sensordata.SensorDataDAOImp;
import com.polesskiy.entity.SensorData;
import com.polesskiy.service.EMF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 * SensorData Service implementation
 */
@Service("sensorDataService")
@Transactional
public class SensorDataServiceImp implements SensorDataService {
//    @Autowired
    private SensorDataDAO sensorDataDAO = new SensorDataDAOImp();

    @Override
    public void saveSensorData(SensorData sensorData) {
        EntityManager entityManager = EMF.get().createEntityManager();
        sensorDataDAO.setEntityManager(entityManager);

        try {
            entityManager.getTransaction().begin();
            sensorDataDAO.save(sensorData);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.printf("Maybe %s: %s already exists in database\r\n", sensorData.getClass(), sensorData);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
