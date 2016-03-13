import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.*;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class UserServiceImpTest {
    UserServiceImp userServiceImp = new UserServiceImp();

    @Test
    public void testUserCRUD() throws Exception {
        //init test sensor data
        User testUser = new User();
        testUser.setLogin("test@mail.mail");

        //add to DB
        Assert.assertTrue(userServiceImp.add(testUser));

        //get from DB
        Assert.assertNotNull(userServiceImp.getByLogin(testUser.getLogin()));

        //TODO update test here

        //delete from DB
        userServiceImp.deleteByLogin(testUser.getLogin());
    }

    @Test
    public void testGetAll() {
        //get all from db
        List<User> userList = userServiceImp.getAllUsers();
        for (User user : userList) System.out.println(user);
    }

    @Test
    public void testGetAllSensors(){
//        TODO implement this test
    }

}
