package com.polesskiy.dao.sensor;

import com.polesskiy.dao.GenericDAOImp;
import com.polesskiy.entity.Sensor;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

/**
 * Sensor DAO implementation
 */
@Repository("sensorDAO")
public class SensorDAOImp extends GenericDAOImp<Sensor, Long> implements SensorDAO {
    public SensorDAOImp() {
        super(Sensor.class);
    }

    @Override
    public Sensor find(String userLogin, long id) {
        TypedQuery<Sensor> query = super.getEntityManager().createQuery(
                "SELECT c from Sensor c WHERE c.id=:id AND c.ownerUser.login=:userLogin",
                Sensor.class).setParameter("id", id).setParameter("userLogin", userLogin);

        return query.getSingleResult();
    }
}
