package layers.service;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.SensorData;
import com.polesskiy.entity.User;
import com.polesskiy.service.sensor.SensorServiceImp;
import com.polesskiy.service.sensordata.SensorDataService;
import com.polesskiy.service.sensordata.SensorDataServiceImp;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by polesskiy on 29.03.16.
 */
public class SensorDataServiceTest {
    UserService userService = new UserServiceImp();
    private User user;
    private Sensor sensor;

    {
        user = new User("sensorDataService@test", "pass", new HashSet<Sensor>() {{
            add(sensor);
        }});
        sensor = new Sensor(user, "sensorDataServiceTest", "no info", null);
    }


    @Before
    public void saveSensor() {
        userService.saveUser(user);
        new SensorServiceImp().saveSensor(sensor);
    }

    @After
    public void deleteSensor() {
        System.out.println("Deleting test user");
        userService.deleteUser(user.getLogin());
    }

    @Test
    public void saveSensorDataTest() {
        SensorDataService sensorDataService = new SensorDataServiceImp();

        SensorData sensorData = new SensorData(sensor, new Date(), new HashMap<String, String>() {{
            put("testKey", "testValue");
        }});
        sensorDataService.saveSensorData(sensorData);

        System.out.printf("User from DB, after saving SensorData %s:\r\n%s\r\n", sensorData, userService.findUser(user.getLogin()));
    }
}
