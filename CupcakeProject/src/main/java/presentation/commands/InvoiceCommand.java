package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Michael N. Korsgaard
 */
public class InvoiceCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * Checks whether user is admin or a customer and redirects the user to
     * either customer-invoice or admin site.
     *
     * @param request html request from FrontController
     * @param response html response from FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters and set initial nextJspPage String
        String jspPage = "invoice";

        // Set Attributes and go to next Page
        return jspPage;
    }
}
