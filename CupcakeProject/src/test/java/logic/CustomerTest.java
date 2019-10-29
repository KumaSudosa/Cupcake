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

public class CustomerTest {
    
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
    public void testCreateNewCustomer() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "michael";
        String pw = "and51Ae";
        String mail = "cphmichael@mail.com";
        double balance = 50.0;
        String role = "c";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);

        //assert
        assertEquals(brugerNavn, User.getUserList().get(0).getUsername());
        assertEquals(pw, User.getUserList().get(0).getPassword());
        assertEquals(mail, User.getUserList().get(0).getEmail());
        assertEquals(balance, User.getUserList().get(0).getNewUserBalance(), 50.0);
        assertEquals(role, User.getUserList().get(0).getRole());
    }
    
    @Test
    public void testMultipleCreateCustomerFromDbCalls() {
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
        Double expectedBalance = 50.0;
        String expectedRole = "c";

        assertEquals(expectedSize, User.getUserList().size());
        assertTrue(expectedUsername.equals(result.getUsername()));
        assertTrue(expectedEmail.equals(result.getEmail()));
        assertTrue(expectedPassword.equals(result.getPassword()));
        assertEquals(expectedBalance, result.getNewUserBalance(), 50);
        assertEquals(expectedRole, result.getRole(), "c");
    }
    
    @Test
    public void testCreateCustomerConstructor() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
        double funds = 50;

        //act
        Customer result = new Customer(brugerNavn, pw, mail, funds);

        //assert
        assertEquals(brugerNavn, result.getUsername());
        assertEquals(pw, result.getPassword());
        assertEquals(mail, result.getEmail());
        assertEquals(funds, result.getNewUserBalance(), 0);
        assertEquals(1, User.getUserList().size());
    }
    
    
    @Test
    public void testCorrectLoginCustomer() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        String role = "c";
        
        Customer customer = new Customer(brugerNavn, pw, mail, funds);
        assertNull(customer.getShoppingCart());

        //act
        User result = User.LoginUser(mail, pw);

        //assert
        assertEquals(customer, result);
        assertNotNull(customer.getShoppingCart());
    }

    @Test
    public void testCorrectLoginAfterPreviousLogin() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 0;
        String role = "c";

        Customer customer = new Customer(brugerNavn, pw, mail, funds);
        User.LoginUser(mail, pw);
        ShoppingCart currentShoppingCart = customer.getShoppingCart();

        //act
        User result = User.LoginUser(mail, pw);

        //assert
        assertEquals(customer, result);
        assertEquals(currentShoppingCart, customer.getShoppingCart());
    }
}