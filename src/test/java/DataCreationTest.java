import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.SensorData;
import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.Test;

import java.util.*;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataCreationTest {
    UserService userService = new UserServiceImp();

    @Test
    public void testCreateTestDataSuite() {

        //init test user data
        User testUser = new User("user@test", "pass", null);

        //test sensors
        final Sensor airSensor = new Sensor(
                testUser,
                "Air sensor",
                "Air sensor for temperature and humidity measuring.",
                null);


        final Sensor weatherSensor = new Sensor(
                testUser,
                "Weather sensor",
                "Weather sensor for pressure measuring and indicating  whether there is rain.",
                null);


        final Random random = new Random();

        //fill air sensor
        airSensor.setSensorDataList(new HashSet<SensorData>() {{
            for (int i = 0; i < 5; i++) {
                final int temperatureValue = random.nextInt(100);
                final int humidityValue = random.nextInt(100);
                SensorData testSensorData = new SensorData();

                //set owner sensor
                testSensorData.setOwnerSensor(airSensor);

                Date testDate = new Date();
                testDate.setHours(testDate.getHours() + i);
                testSensorData.setDate(testDate);

                testSensorData.setData(new HashMap<String, String>() {{
                    put("Temperature, C", String.format("%d", temperatureValue));
                    put("Humidity, %", String.format("%d", humidityValue));
                }});

                add(testSensorData);
            }
        }});

        //fill weather sensor
        weatherSensor.setSensorDataList(new HashSet<SensorData>() {{
            for (int i = 0; i < 5; i++) {
                final int pressureValue = random.nextInt(50) + 725;
                final boolean isRain = random.nextBoolean();
                SensorData testSensorData = new SensorData();

                //set owner sensor
                testSensorData.setOwnerSensor(weatherSensor);

                Date testDate = new Date();
                testDate.setHours(testDate.getHours() + i);
                testSensorData.setDate(testDate);

                testSensorData.setData(new HashMap<String, String>() {{
                    put("Pressure, mmHg", String.format("%d", pressureValue));
                    put("isRain", String.format("%b", isRain));
                }});

                add(testSensorData);
            }
        }});

        testUser.setSensors(new HashSet<Sensor>() {{
            add(airSensor);
            add(weatherSensor);
        }});


        //add test user to db
        if (userService.isUserExists(testUser)) {
            System.out.printf("User with login: %s already exists. Replacing by User: %s\r\n", testUser.getLogin(), testUser);
            userService.deleteUser(testUser.getLogin());
            userService.saveUser(testUser);
        } else {
            System.out.printf("Saving User: %s\r\n", testUser);
            userService.saveUser(testUser);
        }
    }
}

