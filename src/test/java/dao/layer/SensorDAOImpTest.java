package dao.layer;

import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class SensorDAOImpTest {
    SensorDAOImp sensorDAOImp = new SensorDAOImp();

    @Test
    public void testSensorCRUD() throws Exception {
        UserDAOImp userDAOImp = new UserDAOImp();

        //init test user data
        User testUser = new User();
        testUser.setLogin("test@test");

        //add test user to db
        userDAOImp.add(testUser);

        //init test sensor data
        Sensor testSensor = new Sensor(testUser, "testSensorName", "This is test sensor additional info", null);

        //add sensor to DB
        Sensor testSensorFromDB = sensorDAOImp.add(testSensor);
        Assert.assertTrue(testSensor != null);

        //get sensor from DB
        Assert.assertNotNull(sensorDAOImp.getById(testSensorFromDB.getId()));

        //TODO test update here

        //delete user from DB, users sensors will be deleted automatically
        userDAOImp.deleteByLogin(testUser.getLogin());
    }

    @Ignore
    @Test
    public void testGetAll() {
        //get all from db
        List<Sensor> sensorList = sensorDAOImp.getAllSensors();
        System.out.println("All sensors:");
        for (Sensor sensor : sensorList) System.out.println(sensor);
    }

    @Ignore
    @Test
    public void deleteAll() {
        List<Sensor> sensorList = sensorDAOImp.getAllSensors();
        for (Sensor sensor : sensorList) sensorDAOImp.deleteById(sensor.getId());
    }
}
