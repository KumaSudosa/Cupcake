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
import persistence.IUserMapper;
import static org.junit.Assert.*;

/**
 *
 * @author cahit
 */
public class UserTest {
    IUserMapper userMapper;
    
    @Before
    public void setup() {

        FakeUserMapper fakeMapper = new FakeUserMapper();
        User.getUserList().clear();
        String[][] users = {
            {"cahit", "12345", "cph@gmail.com", "100"},
        };
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
        assertEquals(funds, result.getBalance(),0.0);
        assertEquals(1, User.getUserList().size());
        
        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserMethodDublicateEmail() {
        //arrange
        String brugerNavn = "andreas";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserPasswordMismatch() {
        //arrange
        String brugerNavn = "Marcus";
        String pw = "and51Ae";
        String pw2 = "and51AE";
        String mail = "marc@hotmail.com";
        //act
        User.RegisterUser (brugerNavn, pw, pw2, mail);
        
    }
    
    @Test
    public void testCreateNewUser() {
        //arrange
        String brugerNavn = "michael";
        String pw = "and51Ae";
        String mail = "cphmichael@mail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
        //assert
        int expectedBalance = 0;
        assertEquals(brugerNavn, User.getUserList().get(0).getUsername());
        assertEquals(pw, User.getUserList().get(0).getPassword());
        assertEquals(mail, User.getUserList().get(0).getEmail());
        assertEquals(expectedBalance, User.getUserList().get(0).getBalance(),0.0);
    }

    @Test
    public void testCreateUserFromDb() {
        //act
        User.createUsersFromDB();
        //assert
        int expectedSize = userMapper.getUserList().size();
        assertEquals(expectedSize, User.getUserList().size());
    }
    
    @Test
    public void testMultipleCreatUserFromDbCalls() {
        //act
        for (int i = 0; i < 10; i++) {
        User.createUsersFromDB();    
        }
        //assert
        int expectedSize = userMapper.getUserList().size();
        assertEquals(expectedSize, User.getUserList().size());
    }
    
    @Test (expected = LoginException.class)
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
    public void testCorrectLoginPw() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        //act
        User result = User.LoginUser(mail, pw);
        assertEquals(user, result);
    }
    
    @Test (expected = LoginException.class)
    public void testNoMatchingUser() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        //act
        User result = new User(brugerNavn, pw, mail, funds);
        User.LoginUser("Hassan", pw);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserNoUppercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hej123";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserNoLowercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "HEJ123";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserNoNumbersInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hejHej";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserWithNoPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "      ";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testCreateUserPwToLong() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "D3tteEr21TegnLangt1kk";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

}