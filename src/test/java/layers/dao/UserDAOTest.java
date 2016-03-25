package layers.dao;

import com.polesskiy.entity.User;
import com.polesskiy.fasade.dao.user.UserDAOImp;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by polesskiy on 24.03.16.
 */
public class UserDAOTest {
    UserDAOImp userDAOImp = new UserDAOImp();

    {
        EntityManager entityManager = Persistence.createEntityManagerFactory("SENSORS-TRANSACTIONS").createEntityManager();
        userDAOImp.setEntityManager(entityManager);
    }

    @Test
    public void testCRUDUserDAO() {
        User user = new User();
        user.setLogin("testFasade@test");
        //save
        userDAOImp.save(user);

        //find
        user = userDAOImp.find(user.getLogin());
        System.out.printf("Found user: %s\r\n", user);

        //update
        user = userDAOImp.edit(user);
        System.out.printf("Updated user: %s\r\n", user);

        //delete
        userDAOImp.delete(user);
        System.out.printf("Deleting user: %s\r\n", user);

        System.out.printf("All users:\r\n");
        for (User user1 : userDAOImp.getAllUsers()) System.out.println(user1);
    }
}
