package presentation;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;

/**
 * 
 * @author Marcus
 */

public class RegistrationCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User.createUsersFromDB();
        String nextJspPage = "login";
        String username = request.getParameter ("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("passwordRepeat");
        String email = request.getParameter("email");
        try {
            User.RegisterUser(username, password, password2, email);
        } catch (IllegalArgumentException exception) {
            request.setAttribute("RegistrationError", exception);
            nextJspPage = "registration";
        }
        return nextJspPage;
    }
}