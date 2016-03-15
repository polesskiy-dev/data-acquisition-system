package dao.layer;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.SensorData;
import com.polesskiy.entity.User;
import com.polesskiy.dao.sensor.SensorDAOImp;
import com.polesskiy.dao.sensordata.SensorDataDAOImp;
import com.polesskiy.dao.user.UserDAOImp;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by polesskiy on 13.03.16.
 */
public class SensorDataDAOImpTest {
    UserDAOImp userDAOImp = new UserDAOImp();
    SensorDAOImp sensorDAOImp = new SensorDAOImp();
    SensorDataDAOImp sensorDataDAOImp = new SensorDataDAOImp();

    @Ignore
    @Test
    public void testSensorData() throws Exception {
        //init test user data
        User testUser = new User();
        testUser.setLogin("testUser@forSensorData");

        //init test sensor data
        Sensor testSensor = new Sensor();
        testSensor.setOwnerUser(testUser);

        //add to db
        userDAOImp.add(testUser);
        Sensor testSensorFromDB = sensorDAOImp.add(testSensor);

        //init test sensor data
        SensorData testSensorData = new SensorData();
        testSensorData.setOwnerSensor(testSensorFromDB);

        testSensorData.setDate(new Date());
        testSensorData.setData(new HashMap<String, String>(){{
            put("temperature","11C");
            put("humidity","61%");
        }});

        //add test sensor data to db
        sensorDataDAOImp.add(testSensorData);
    }

    @Test
    public void testGetAll() {
        //get all from db
        List<SensorData> sensorsDataList = sensorDataDAOImp.getAllSensorsData();
        System.out.println("All sensors data:");
        for (SensorData sensorData : sensorsDataList) System.out.println(sensorData);
    }
}
