import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.*;

import java.util.List;

/**
 * Created by polesskiy on 12.03.16.
 */
public class UserServiceImpTest {
    UserServiceImp userServiceImp = new UserServiceImp();

    //Create user
    User testUser = new User();//Create user

    @Before
    public void initUser() {
        this.testUser.setLogin("test@email.mail");
    }

    @Test
    public void testSaveUser() throws Exception {
        //store to db
        boolean savingToDBResult = userServiceImp.add(testUser);
        Assert.assertTrue(savingToDBResult);
    }

    @Test
    public void testDeleteUser() throws Exception {
        //delete from db
        userServiceImp.deleteByLogin(testUser.getLogin());
    }

    @Test
    public void testGetUser() throws Exception {
        String testLogin = testUser.getLogin();
        //get from db
        Assert.assertEquals(userServiceImp.getByLogin(testLogin).getLogin(), testLogin);
    }

    @Test
    public void testGetAll() {
        List<User> userList = userServiceImp.getAllUsers();
        for (User user : userList) System.out.println(user);
    }

}
