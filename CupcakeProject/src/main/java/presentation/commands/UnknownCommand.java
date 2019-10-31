package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

public class UnknownCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * is used every time there occurs an error due to an unknown command call.
     *
     * throws IllegalArgumentException
     *
     * @param request html request from FrontController
     * @param response html response from FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters and set initial nextJspPage String
        String msg = "Unknown command. Contact IT";
        throw new IllegalArgumentException(msg);
    }
}
