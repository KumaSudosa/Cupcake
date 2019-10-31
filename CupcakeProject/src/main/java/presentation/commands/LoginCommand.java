package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;
import presentation.Command;

/**
 * @author Michael & Marcus Get Parameters and set initial nextJspPage String
 * Logic call in order to find attribute.
 * @throws
 */
public class LoginCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * checks whether user is a customer or admin.
     *
     * catches LoginException
     *
     * @param request html request from FrontController
     * @param response html response from FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

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
