package dao.layer;

import com.polesskiy.entity.User;
import com.polesskiy.dao.user.UserDAOImp;
import org.junit.*;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class UserDAOImpTest {
    UserDAOImp userDAOImp = new UserDAOImp();

    @Test
    public void testUserCRUD() throws Exception {
        //init test sensor data
        User testUser = new User();
        testUser.setLogin("test@mail.mail");

        //add to DB
        Assert.assertTrue(userDAOImp.add(testUser));

        //get from DB
        Assert.assertNotNull(userDAOImp.getByLogin(testUser.getLogin()));

        //TODO update test here

        //delete from DB
        userDAOImp.deleteByLogin(testUser.getLogin());
    }

    @Test
    public void testGetAll() {
        //get all from db
        List<User> userList = userDAOImp.getAllUsers();
        System.out.println("All users:");
        for (User user : userList) System.out.println(user);
    }

    @Test
    public void testGetAllSensors(){
//        TODO implement this test
    }

}
