package dao.layer;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.dao.user.UserDAOImp;
import org.junit.*;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class SensorDAOImpTest {
    SensorDAOImp sensorDAOImp = new SensorDAOImp();

    @Ignore
    @Test
    public void testSensorCRUD() throws Exception {
        //init test user data
        User testUser = new User();
        testUser.setLogin("test@test");

        //add test user to db
        UserDAOImp userDAOImp = new UserDAOImp();
        userDAOImp.add(testUser);

        //init test sensor data
        Sensor testSensor = new Sensor();
        testSensor.setOwnerUser(testUser);
        testSensor.setName("testSensorName");
        testSensor.setAdditionalInfo("This is test sensor additional info");

        //add sensor to DB
        Sensor testSensorFromDB = sensorDAOImp.add(testSensor);
        Assert.assertTrue(testSensor != null);

        //get sensor from DB
        Assert.assertNotNull(sensorDAOImp.getById(testSensorFromDB.getId()));

        //TODO test update here

        //delete sensor from DB
        sensorDAOImp.deleteById(testSensorFromDB.getId());

        //delete user from DB
        userDAOImp.deleteByLogin(testUser.getLogin());
    }

    @Test
    public void testGetAll() {
        //get all from db
        List<Sensor> sensorList = sensorDAOImp.getAllSensors();
        System.out.println("All sensors:");
        for (Sensor sensor : sensorList) System.out.println(sensor);
    }
}
