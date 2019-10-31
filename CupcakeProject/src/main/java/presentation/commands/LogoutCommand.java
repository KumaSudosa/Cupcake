package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

public class LogoutCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * logs-out the admin or customer to main-page by setting the session to
     * null
     *
     * @param request html request from FrontController
     * @param response html response from FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "index";

        // Logic calls
        request.getSession().setAttribute("user", null);

        // Set Attributes and go to next Page
        return NextJspPage;
    }
}
