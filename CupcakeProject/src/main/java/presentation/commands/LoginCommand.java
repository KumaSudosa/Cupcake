package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.User;
import presentation.Command;

/**
 * 
 * @author Michael N. Korsgaard
 */

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        User.createUsersFromDB();
        String nextJspPage = "shoppage";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (User == admin) {
            
        } else {
        try {
            User user = User.LoginUser(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } catch (LoginException exception) {
            request.setAttribute("LoginError", exception);
            nextJspPage = "login";
        }
        }
        return nextJspPage;
    }
}