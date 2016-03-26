package layers.service;

import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by polesskiy on 25.03.16.
 */
public class UserServiceTest {
    UserService userService = new UserServiceImp();

    @Test
    public void UserCRUDTest() {
        User user = new User("testServiceUser@login", "testServiceUserPassword", null);

        //save
        userService.saveUser(user);

        //find
        user = userService.findUser(user.getLogin());
        System.out.printf("Found user: %s\r\n", user);

        //update
        user = userService.editUser(user);
        System.out.printf("Updated user: %s\r\n", user);

        //delete
        userService.deleteUser(user.getLogin());
        System.out.printf("Deleting user: %s\r\n", user);

        System.out.printf("All users:\r\n");
        for (User user1 : userService.getAllUsers()) System.out.println(user1);
    }

    @Test
    /**
     * Test additional functions
     * isUserExists
     * deleteAll
     */
    public void UserAdditionalTest() {
        User user = new User("1", null, null);
        userService.saveUser(user);

        User user1 = new User("2", null, null);
        userService.saveUser(user1);

        //isUserExists
        Assert.assertTrue(userService.isUserExists(user));

        //delete all
        userService.deleteAllUsers();

        Assert.assertTrue(userService.getAllUsers().isEmpty());
    }
}
