package logic;

import javax.security.auth.login.LoginException;
import persistence.mappers.UserMapper;

/**
 *
 * @author andre
 */
public class Admin extends User{
    
    public Admin(String username, String password, String email, String role) {
        super(username, password, email, role);
    }
    
    public void changeCustomerBalance(Customer user, int newBalance) {
        
    }
    
}
