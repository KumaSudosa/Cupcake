package persistence;

import java.util.HashMap;
import javax.security.auth.login.LoginException;
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
    public void TestCreateUserConstructor() {
        //arrange
        String brugerNavn = "cahit";
        String pw = "12345";
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
    public void TestCreateUserMethodDublicateUsername() {
        //arrange
        String brugerNavn = "cahit";
        String pw = "12345";
        String mail = "cph2@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void TestCreateUserMethodDublicateEmail() {
        //arrange
        String brugerNavn = "andreas";
        String pw = "12345";
        String mail = "cph@gmail.com";
        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void TestCreateUserPasswordMismatch() {
        //arrange
        String brugerNavn = "Marcus";
        String pw = "12345";
        String pw2 = "1234";
        String mail = "marc@hotmail.com";
        //act
        User.RegisterUser (brugerNavn, pw, pw2, mail);
        
    }
    
    @Test
    public void TestCreateNewUser() {
        //arrange
        String brugerNavn = "michael";
        String pw = "12345";
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
    public void TestCreateUserFromDb() {
        //act
        User.createUsersFromDB();
        //assert
        int expectedSize = userMapper.getUserList().size();
        assertEquals(expectedSize, User.getUserList().size());
    }
    
    @Test
    public void TestMultipleCreatUserFromDbCalls() {
        //act
        for (int i = 0; i < 10; i++) {
        User.createUsersFromDB();    
        }
        //assert
        int expectedSize = userMapper.getUserList().size();
        assertEquals(expectedSize, User.getUserList().size());
    }
    
    @Test (expected = LoginException.class)
    public void TestWrongLoginPw() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "12345";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User result = new User(brugerNavn, pw, mail, funds);
        //act
        User.LoginUser(brugerNavn, "3214");
    }
    
    @Test
    public void TestCorrectLoginPw() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "12345";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        //act
        User result = User.LoginUser(brugerNavn, pw);
        assertEquals(user, result);
    }
    
    @Test (expected = LoginException.class)
    public void TestNoMatchingUser() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "12345";
        String mail = "cph2@gmail.com";
        double funds = 0;
        User user = new User(brugerNavn, pw, mail, funds);
        //act
        User result = new User(brugerNavn, pw, mail, funds);
        User.LoginUser("Hassan", pw);
    }

}
