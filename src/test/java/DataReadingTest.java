import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by polesskiy on 13.03.16.
 */
public class DataReadingTest {
    UserService userService = new UserServiceImp();

    @Test
    public void readUserAndSensors() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        //add test user to db
        Collection<User> users = userService.getAllUsers();

        System.out.println("All users:");
        for (User user : users) {
            System.out.printf("%s\r\n", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
        }
    }
}
