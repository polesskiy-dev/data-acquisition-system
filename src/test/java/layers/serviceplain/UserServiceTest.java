package layers.serviceplain;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.service.user.UserService;
import com.polesskiy.fasade.service.user.plainimplementation.UserServiceImp;
import org.junit.Test;

/**
 * Created by polesskiy on 25.03.16.
 */
public class UserServiceTest {

    UserService userService = new UserServiceImp();

    @Test
    public void UserCRUDTest() {
        User user = new User();
        user.setLogin("testServiceUser@login");

        userService.saveUser(user);
    }
}
