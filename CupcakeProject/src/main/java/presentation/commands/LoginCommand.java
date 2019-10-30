package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;
import presentation.Command;

/**
 * @author Michael & Marcus
 */

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        
        // Get parameters and set initial nextJspPage String
        String nextJspPage = "";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Logic calls
        try {
            User user = User.LoginUser(email, password);
            request.getSession().setAttribute("user", user);
            
            if (User.isUserAdmin(user)) {
                
                nextJspPage = "admin";
            } else {
                nextJspPage = "shoppage"; 
            }
            
        } catch (LoginException exception) {
            request.setAttribute("LoginError", exception);
            nextJspPage = "login";
        }
        return nextJspPage;
    }
}