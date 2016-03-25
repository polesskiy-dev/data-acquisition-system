package layers.dao;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.sensor.SensorDAOImp;
import com.polesskiy.fasade.dao.user.UserDAOImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by polesskiy on 24.03.16.
 */
public class SensorDAOTest {
    SensorDAOImp sensorDAOImp = new SensorDAOImp();
    UserDAOImp userDAOImp = new UserDAOImp();
    User user = null;

    {
        EntityManager entityManager = Persistence.createEntityManagerFactory("SENSORS-TRANSACTIONS").createEntityManager();
        sensorDAOImp.setEntityManager(entityManager);
        userDAOImp.setEntityManager(entityManager);
    }

    @Before
    public void createTestUser() {
        user = new User();
        user.setLogin("sensorDAOTestUser@test");
        //save user
        userDAOImp.save(user);
    }

    @After
    public void clearTestUser() {
        //delete
        userDAOImp.delete(user);
    }

    @Test
    public void testDAOSensorCRUD() {
        Sensor sensor = new Sensor(user, "sensorDAOTestSensor", "no info", null);

        //save
        sensorDAOImp.save(sensor);

        //find
        sensor = sensorDAOImp.find(sensor.getName());
        System.out.printf("Found sensor: %s\r\n", sensor);

        //update
        sensor = sensorDAOImp.edit(sensor);
        System.out.printf("Updated sensor: %s\r\n", sensor);

        System.out.printf("Users: %s\r\n", userDAOImp.getAllUsers());

        //delete
        System.out.printf("Deleting sensor: %s\r\n", sensor);
        sensorDAOImp.delete(sensor);
    }
}
