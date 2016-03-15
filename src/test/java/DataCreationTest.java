import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.dao.user.UserDAOImp;
import org.junit.Test;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataCreationTest {
    SensorDAOImp sensorDAOImp = new SensorDAOImp();
    UserDAOImp userDAOImp = new UserDAOImp();

    @Test
    public void createUserAndSensors() {
        //init test user data
        User testUser = new User();
        testUser.setLogin("testUser@test" + System.currentTimeMillis());

        //add test user to db
        userDAOImp.add(testUser);

        //init test sensor data
        for (int i = 0; i < 5; i++) {
            Sensor testSensor = new Sensor();
            testSensor.setOwnerUser(testUser);
            testSensor.setName("testSensor" + i);
            testSensor.setAdditionalInfo(String.format(new String("sensor %d additional info"), i));

            //add sensor to DB
            sensorDAOImp.add(testSensor);
        }
    }
}
