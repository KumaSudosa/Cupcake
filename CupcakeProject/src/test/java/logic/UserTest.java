package logic;

import java.util.HashMap;
import javax.security.auth.login.LoginException;
import org.junit.Before;
import org.junit.Test;
import persistence.FakeUserMapper;
import static org.junit.Assert.*;
import persistence.mappers.UserMapperInterface;

/**
 * @author cahit
 */

public class UserTest {

    UserMapperInterface userMapper;

    @Before
    public void setup() {

        FakeUserMapper fakeMapper = new FakeUserMapper();
        User.getUserList().clear();
        String[][] users = {
            {"cahit", "and51Ae", "cph@gmail.com", "50", "c"},};
        for (String[] user : users) {
            HashMap<String, String> map = new HashMap();
            map.put("username", user[0]);
            map.put("login", user[1]);
            map.put("email", user[2]);
            map.put("balance", user[3]);
            map.put("role", user[4]);
            fakeMapper.addUserInfo(map);
        }
        userMapper = fakeMapper;
        User.setupUserClass(userMapper);
    }
    
    
    @Test
    public void testCreateUsersFromDb() {
        //arrange
        User.getUserList().clear();
        //act
        User.createUsersFromDB();

        //assert
        int expectedSize = userMapper.getUserList().size();
        User result = User.getUserList().get(0);
        String expectedUsername = "cahit";
        String expectedPassword = "and51Ae";
        String expectedEmail = "cph@gmail.com";
        Double expectedBalance = 50.0;
        String expectedRole = "c";
        
        assertEquals(expectedSize, User.getUserList().size());
        assertTrue(expectedUsername.equals(result.getUsername()));
        assertTrue(expectedEmail.equals(result.getEmail()));
        assertTrue(expectedPassword.equals(result.getPassword()));
        assertEquals(expectedBalance, result.getNewUserBalance(), 50);
        assertTrue(expectedRole.equals(result.getRole()));
    }
}