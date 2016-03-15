import com.polesskiy.entity.User;
import com.polesskiy.dao.user.UserDAOImp;
import org.junit.Test;

import java.util.List;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataReadingTest {
    UserDAOImp userDAOImp = new UserDAOImp();

    @Test
    public void readUserAndSensors() {
        //add test user to db
        List<User> users = userDAOImp.getAllUsers();

        for (User user : users) {
            System.out.printf("%s\r\n", user);
        }
    }
}
