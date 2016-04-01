package layers.service;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.service.sensor.SensorService;
import com.polesskiy.service.sensor.SensorServiceImp;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by polesskiy on 29.03.16.
 */
public class SensorServiceTest {
    private UserService userService = new UserServiceImp();
    private SensorService sensorService = new SensorServiceImp();

    User user = new User("sensorService@test", "pass", null);

    @Before
    public void saveUser() {
        userService.saveUser(this.user);
    }

    @After
    public void deleteUser() {
        userService.deleteUser(this.user.getLogin());
    }

    @Test
    public void sensorCRUDTest() {
        Sensor sensor = new Sensor(user, "sensorServiceTest", "no info", null);

        //save
        sensorService.saveSensor(sensor);
        System.out.printf("Sensor saved, id=%d\r\n",sensor.getId());

        //find by id
        sensor = sensorService.findSensor(sensor.getId());
        System.out.printf("Found sensor: %s\r\n", sensor);

        //find by users login and id
        sensor = sensorService.findSensor(user.getLogin(), sensor.getId());
        System.out.printf("Found sensor: %s, belonging to user: %s\r\n", sensor, user.getLogin());

        //update
        sensor = sensorService.editSensor(sensor);
        System.out.printf("Updated sensor: %s\r\n", sensor);

        //delete
        sensorService.deleteSensor(sensor.getId());
        System.out.printf("Deleting sensor: %s\r\n", sensor);
    }
}
