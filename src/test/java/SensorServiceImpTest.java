import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.service.sensor.SensorServiceImp;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.*;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class SensorServiceImpTest {
    SensorServiceImp sensorServiceImp = new SensorServiceImp();

    @Test
    public void testSensorCRUD() throws Exception {
        //init test sensor data
        Sensor testSensor = new Sensor();
        testSensor.setName("testSensorName");
        testSensor.setAdditionalInfo("This is test sensor additional info");

        //add to DB
        Sensor testSensorFromDB = sensorServiceImp.add(testSensor);
        Assert.assertTrue(testSensor != null);

        //get from DB
        Assert.assertNotNull(sensorServiceImp.getById(testSensorFromDB.getId()));

        //TODO test update here

        //delete from DB
        sensorServiceImp.deleteById(testSensorFromDB.getId());
    }

    @Test
    public void testGetAll() {
        //get all from db
        List<Sensor> sensorList = sensorServiceImp.getAllSensors();
        for (Sensor sensor : sensorList) System.out.println(sensor);
    }
}
