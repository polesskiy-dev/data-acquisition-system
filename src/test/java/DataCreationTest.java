import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.dao.sensordata.SensorDataDAOImp;
import com.polesskiy.dao.user.UserDAOImp;
import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.SensorData;
import com.polesskiy.entity.User;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataCreationTest {
    SensorDAOImp sensorDAOImp = new SensorDAOImp();
    UserDAOImp userDAOImp = new UserDAOImp();
    SensorDataDAOImp sensorDataDAOImp = new SensorDataDAOImp();

    @Test
    public void createTestDataSuite() {
        //init test user data
        User testUser = new User();
        testUser.setLogin("testuser@gmail.com");

        //add test user to db
        userDAOImp.add(testUser);

        //test sensors
        Sensor airSensor = new Sensor(
                testUser,
                "Air sensor",
                "Air sensor for temperature and humidity measuring.",
                null);


        Sensor weatherSensor = new Sensor(
                testUser,
                "Weather sensor",
                "Weather sensor for pressure measuring and indicating  whether there is rain.",
                null);

        //add sensor to DB
        airSensor = sensorDAOImp.add(airSensor);
        weatherSensor = sensorDAOImp.add(weatherSensor);

        //init test sensor data
        Random random = new Random();
        //fill air sensor
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
                put("Temperature", String.format("%d C", temperatureValue));
                put("Humidity", String.format("%d %%", humidityValue));
            }});

            //save to db
            sensorDataDAOImp.add(testSensorData);
        }

        //fill weather sensor
        for (int i = 0; i < 10; i++) {
            final int pressureValue = random.nextInt(50) + 725;
            final boolean isRain = random.nextBoolean();
            SensorData testSensorData = new SensorData();

            //set owner sensor
            testSensorData.setOwnerSensor(weatherSensor);

            Date testDate = new Date();
            testDate.setHours(testDate.getHours() + i);
            testSensorData.setDate(testDate);

            testSensorData.setData(new HashMap<String, String>() {{
                put("Pressure", String.format("%d mmHg", pressureValue));
                put("isRain", String.format("%b", isRain));
            }});

            //save to db
            sensorDataDAOImp.add(testSensorData);
        }
    }
}

