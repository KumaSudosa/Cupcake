package presentation.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;
import presentation.Command;

/**
 * @author Marcus
 */
public class RegistrationCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters and set initial nextJspPage String
        User.createUsersFromDB();
        String nextJspPage = "login";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("passwordRepeat");
        String email = request.getParameter("email");

        // Logic calls
        try {
            User.RegisterUser(username, password, password2, email);
        } // Set Attributes and go to next Page
        catch (IllegalArgumentException exception) {
            request.setAttribute("RegistrationError", exception);
            nextJspPage = "registration";
        }
        return nextJspPage;
    }
}