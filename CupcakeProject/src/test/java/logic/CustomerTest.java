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
        User.setupUserClass(userMapper);
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
        assertTrue(expectedRole.equals(result.getRole()));
    }
    
    @Test
    public void testCreateCustomerConstructor() {
        //arrange
        User.getUserList().clear();
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";
        String role = "c";
        double funds = 50;

        //act
        Customer result = new Customer(brugerNavn, pw, mail, role, funds);

        //assert
        assertEquals(brugerNavn, result.getUsername());
        assertEquals(pw, result.getPassword());
        assertEquals(mail, result.getEmail());
        assertEquals(role, result.getRole());
        assertEquals(funds, result.getNewUserBalance(), 0);
        assertEquals(1, User.getUserList().size());
    }
    
    @Test
    public void testCorrectLoginCustomer() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        double funds = 50;
        String role = "c";
        
        Customer customer = new Customer(brugerNavn, pw, mail, role, funds);
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
        double funds = 50;
        String role = "c";

        Customer customer = new Customer(brugerNavn, pw, mail, role, funds);
        User.LoginUser(mail, pw);
        ShoppingCart currentShoppingCart = customer.getShoppingCart();

        //act
        User result = User.LoginUser(mail, pw);

        //assert
        assertEquals(customer, result);
        assertEquals(currentShoppingCart, customer.getShoppingCart());
    }
    
    /* @Test
    public void testPayForShoppingCart() {
        
    } */
    
    /* @Test
    public void canBalanceCoverPayment() {
        
    } */
    
    
    
    @Test(expected = LoginException.class)
    public void testWrongLoginPwCustomer() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        String role = "c";
        double funds = 50;
        Customer result = new Customer(brugerNavn, pw, mail, role, funds);

        //act
        User.LoginUser(mail, "and63Jm");
    }
    
    @Test(expected = LoginException.class)
    public void testWrongLoginEmailCustomer() throws LoginException {
        //arrange
        String brugerNavn = "cahit";
        String pw = "and51Ae";
        String mail = "cph2@gmail.com";
        String role = "c";
        double funds = 50;
        Customer result = new Customer(brugerNavn, pw, mail, role, funds);

        //act
        User.LoginUser("cph2@hotmail.com", pw);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerMethodDublicateEmail() {
        //arrange
        String brugerNavn = "andreas";
        String pw = "and51Ae";
        String mail = "cph@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerNoUppercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hej123";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerNoLowercaseInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "HEJ123";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerNoNumbersInPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "hejHej";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerWithNoPw() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "      ";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerPwTooLong() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "D3tteEr21TegnLangt1kk";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerPwTooShort() {
        //arrange
        String brugerNavn = "Malte";
        String pw = "aB1";
        String mail = "cph2@gmail.com";

        //act
        User.RegisterUser(brugerNavn, pw, pw, mail);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateCustomerPasswordMismatch() {
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