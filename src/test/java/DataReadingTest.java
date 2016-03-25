import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataReadingTest {
    @Autowired
    UserService userService;

    @Test
    public void readUserAndSensors() {
        //add test user to db
        Collection<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.printf("%s\r\n", user);
        }
    }
}
