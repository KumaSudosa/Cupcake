package logic;

import java.util.HashMap;
import javax.security.auth.login.LoginException;
import logic.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistence.FakeUserMapper;
import static org.junit.Assert.*;
import persistence.mappers.UserMapperInterface;

/**
 *
 * @author cahit
 */
public class UserTest {

    UserMapperInterface userMapper;
    Double expectedNewUserBalance = 0.0;    // Used for the User.RegisterUser() method asserts

    @Before
    public void setup() {

        FakeUserMapper fakeMapper = new FakeUserMapper();
        User.getUserList().clear();
        String[][] users = {
            {"cahit", "and51Ae", "cph@gmail.com", "100"},};
        for (String[] user : users) {
            HashMap<String, String> map = new HashMap();
            map.put("username", user[0]);
            map.put("login", user[1]);
            map.put("email", user[2]);
            map.put("balance", user[3]);
            fakeMapper.addUserInfo(map);
        }
        userMapper = fakeMapper;
        User.setupMapper(userMapper);

    }

    @Test
    public void testCreateUserConstructor() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
        double funds = 100;

        //act
        User result = new User(brugerNavn, pw, mail, funds);

        //assert
        assertEquals(brugerNavn, result.getUsername());
        assertEquals(pw, result.getPassword());
        assertEquals(mail, result.getEmail());
        assertEquals(funds, result.getBalance(), 0.0);
        assertEquals(1, User.getUserList().size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserMethodDublicateEmail() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "andreas";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
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

    @Test
    public void testCreateNewUser() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "michael";
        String pw = "and51Ae";
        String mail = "cphmichael@mail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
        //assert
        assertEquals(brugerNavn, User.getUserList().get(0).getUsername());
        assertEquals(pw, User.getUserList().get(0).getPassword());
        assertEquals(mail, User.getUserList().get(0).getEmail());
        assertEquals(expectedNewUserBalance, User.getUserList().get(0).getBalance(), 0.0);
    }

    @Test
    public void testCreateUserFromDb() {
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
        Double expectedBalance = 100.0;
        assertEquals(expectedSize, User.getUserList().size());
        assertTrue(expectedUsername.equals(result.getUsername()));
        assertTrue(expectedEmail.equals(result.getEmail()));
        assertTrue(expectedPassword.equals(result.getPassword()));
        assertEquals(expectedBalance, result.getBalance(), 0.0);
    }

    @Test
    public void testMultipleCreatUserFromDbCalls() {
        //arrange
        User.getUserList().clear();
        //act
        for (int i = 0; i < 10; i++) {
            User.createUsersFromDB();
        }
        //assert
        int expectedSize = userMapper.getUserList().size();
        assertEquals(expectedSize, User.getUserList().size());
        User result = User.getUserList().get(0);
        String expectedUsername = "cahit";
        String expectedPassword = "and51Ae";
        String expectedEmail = "cph@gmail.com";
        Double expectedBalance = 100.0;
        assertEquals(expectedSize, User.getUserList().size());
        assertTrue(expectedUsername.equals(result.getUsername()));
        assertTrue(expectedEmail.equals(result.getEmail()));
        assertTrue(expectedPassword.equals(result.getPassword()));
        assertEquals(expectedBalance, result.getBalance(), 0.0);
    }

    @Test(expected = LoginException.class)
    public void testWrongLoginPw() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User result = new User(brugerNavn, pw, mail, funds);
        //act
        User.LoginUser(mail, "and63Jm");
    }

    @Test
    public void testCorrectLogin() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        assertNull(user.getShoppingCart());
        //act
        User result = User.LoginUser(mail, pw);
        //assert
        assertEquals(user, result);
        assertNotNull(user.getShoppingCart());
    }
    
    @Test
    public void testCorrectLoginAfterPreviousLogin() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        User.LoginUser(mail, pw);
        ShoppingCart currentShoppingCart = user.getShoppingCart();
        //act
        User result = User.LoginUser(mail, pw);
        //assert
        assertEquals(user, result);
        assertEquals(currentShoppingCart, user.getShoppingCart());
    }

    @Test(expected = LoginException.class)
    public void testNoMatchingUser() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        //act
        User result = new User(brugerNavn, pw, mail, funds);
        User.LoginUser("Hassan@gmail.com", pw);
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
    public void testCreateUserPwToLong() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "D3tteEr21TegnLangt1kk";
        String mail = "cph2@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

}
