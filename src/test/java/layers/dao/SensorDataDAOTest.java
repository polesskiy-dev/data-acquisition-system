package layers.dao;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.SensorData;
import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.sensor.SensorDAOImp;
import com.polesskiy.fasade.dao.sensordata.SensorDataDAOImp;
import com.polesskiy.fasade.dao.user.UserDAOImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by polesskiy on 24.03.16.
 */
public class SensorDataDAOTest {
    UserDAOImp userDAOImp = new UserDAOImp();
    SensorDAOImp sensorDAOImp = new SensorDAOImp();
    SensorDataDAOImp sensorDataDAOImp = new SensorDataDAOImp();

    User user = new User();
    Sensor sensor = new Sensor(user, "sensorDAOTestSensor", "no info", null);

    {
        EntityManager entityManager = Persistence.createEntityManagerFactory("SENSORS-TRANSACTIONS").createEntityManager();
        userDAOImp.setEntityManager(entityManager);
        sensorDAOImp.setEntityManager(entityManager);
        sensorDataDAOImp.setEntityManager(entityManager);
    }

    @Before
    public void createTestUser() {
        user.setLogin("sensorDataDAOTestUser@test");

        //save user
        userDAOImp.save(user);

        //save sensor
        sensorDAOImp.save(sensor);
    }

    @Test
    public void testDAOSensorCRUD() {
        SensorData sensorData = new SensorData(sensor, new Date(), new HashMap<String, String>() {{
            put("testKey", "testValue");
        }});

        //save
        sensorDataDAOImp.save(sensorData);

        //update
        sensorData = sensorDataDAOImp.edit(sensorData);
        System.out.printf("Updated sensorData: %s\r\n", sensorData);

        //find
        sensorData = sensorDataDAOImp.find(sensorData.getId());
        System.out.printf("Found sensorData: %s\r\n", sensorData);

//        System.out.println();

        System.out.printf("Users: %s\r\n", userDAOImp.getAllUsers());

        //delete
        System.out.printf("Deleting sensorData: %s\r\n", sensorData);
        sensorDataDAOImp.delete(sensorData);
    }

    @After
    public void clearTestUser() {
        //delete

        userDAOImp.delete(userDAOImp.find(user.getLogin()));
    }
}
