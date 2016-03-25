package layers.service;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.service.user.UserService;
import com.polesskiy.fasade.service.user.UserServiceImplementation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by polesskiy on 25.03.16.
 */
public class UserServiceTest {

    @Autowired
    UserService userService = new UserServiceImplementation();

    @Test
    public void UserCRUDTest() {
        User user = new User();
        user.setLogin("testServiceUser@login");

        userService.saveUser(user);
    }
}
