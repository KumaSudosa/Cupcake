package persistence;

import java.util.HashMap;
import logic.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cahit
 */
public class UserTest {

    @Before
    public void setup() {

        FakeUserMapper fakeMapper = new FakeUserMapper();
        String[][] users = {
            {"cahit", "12345", "cph@gmail.com", "100"}
        };
        for (String[] user : users) {
            HashMap<String, String> map = new HashMap();
            map.put("username", user[0]);
            map.put("login", user[1]);
            map.put("email", user[2]);
            map.put("balance", user[3]);
            fakeMapper.addUserInfo(map);
        }
        IUserMapper userMapper = fakeMapper;
        User.setupMapper(userMapper);

    }

    public void createUser() {

//String expected = ("Hello-World");
//String actual = Concat.stringSum("hello","world");
//assertEquals(expected, actual);    
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
