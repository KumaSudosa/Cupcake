package logic;

import javax.security.auth.login.LoginException;

/**
 *
 * @author andre
 */
public class Admin extends User{
    
    public Admin(String username, String password, String email) {
        super(username, password, email);
    }
    
}
