package logic;

import java.util.HashMap;
import javax.security.auth.login.LoginException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import persistence.FakeUserMapper;
import persistence.mappers.UserMapperInterface;

/**
 * @author Marcus
 */

public class AdminTest {
    
   UserMapperInterface userMapper;

    @Before
    public void setup() {

        FakeUserMapper fakeMapper = new FakeUserMapper();
        User.getUserList().clear();
        String[][] users = {
            {"cahit", "and51Ae","cph@gmail.com", "a"},};
        for (String[] user : users) {
            HashMap<String, String> map = new HashMap();
            map.put("username", user[0]);
            map.put("login", user[1]);
            map.put("email", user[2]);
            map.put("role", user[3]);
            fakeMapper.addUserInfo(map);
        }
        userMapper = fakeMapper;
        User.setupMapper(userMapper);
    }
    
    @Test
    public void testCorrectLoginAdmin() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        String role = "a";
        
        Admin admin = new Admin(brugerNavn, pw, mail, role);

        //act
        User result = User.LoginUser(mail, pw);

        //assert
        assertEquals(admin, result);
    }
    
    
    @Test
    public void testCreateAdminConstructor() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
        String role = "a";

        //act
        Admin result = new Admin(brugerNavn, pw, mail, role);

        //assert
        assertEquals(brugerNavn, result.getUsername());
        assertEquals(pw, result.getPassword());
        assertEquals(mail, result.getEmail());
        assertEquals(role, result.getRole());
        assertEquals(1, User.getUserList().size());
    }
    
    /* @Test
    public void testChangeCustomerBalance() {
        //arrange
    } */
    
    
    @Test(expected = LoginException.class)
    public void testWrongLoginPwAdmin() throws LoginException {
        //arrange
        String brugerNavn = "Admin";
        String pw = "Admin123";
        String mail = "Admin@gmail.com";
        String role = "a";
        Admin result = new Admin(brugerNavn, pw, mail, role);

        //act
        User.LoginUser(mail, "Admin321");
    }
    
    @Test(expected = LoginException.class)
    public void testWrongLoginEmailAdmin() throws LoginException {
        //arrange
        String brugerNavn = "Admin";
        String pw = "Admin123";
        String mail = "Admin@gmail.com";
        String role = "a";
        Admin result = new Admin(brugerNavn, pw, mail, role);

        //act
        User.LoginUser("Admin@outlook.com", pw);
    }
}