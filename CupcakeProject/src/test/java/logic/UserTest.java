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
        User.setupMapper(userMapper);
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
        
        System.out.println(result.getRole());
        System.out.println(expectedRole);

        assertEquals(expectedSize, User.getUserList().size());
        assertTrue(expectedUsername.equals(result.getUsername()));
        assertTrue(expectedEmail.equals(result.getEmail()));
        assertTrue(expectedPassword.equals(result.getPassword()));
        assertEquals(expectedBalance, result.getNewUserBalance(), 50);
        assertTrue(expectedRole.equals(result.getRole()));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerMethodDublicateEmail() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "andreas";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test(expected = LoginException.class)
    public void testNoMatchingUser() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 50;
        
        Customer result = new Customer (brugerNavn, pw, mail, funds);

        //act
        User.LoginUser("Hassan@gmail.com", pw);
    }
    
    @Test(expected = LoginException.class)
    public void testWrongLoginPw() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 50;
        Customer result = new Customer(brugerNavn, pw, mail, funds);

        //act
        User.LoginUser(mail, "and63Jm");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserNoUppercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hej123";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserNoLowercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "HEJ123";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserNoNumbersInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hejHej";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserWithNoPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "      ";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserPwTooLong() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "D3tteEr21TegnLangt1kk";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserPasswordMismatch() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "Marcus";
        String pw = "and51Ae";
        String pw2 = "and51AE";
        String mail = "marc@hotmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw2, mail);
    }
}