package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Customer;
import logic.Invoice;
import presentation.Command;

/**
 * @author Marcus
 */
public class ConfirmationCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * Confirms order for customer
     *
     * @param request html request from FrontController
     * @param response html response from FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "invoice";
        Customer customer = (Customer) request.getSession().getAttribute("user");

        // Logic calls
        Invoice.convertShoppingCartToNewInvoiceFromUser(customer);

        // Set Attributes and go to next Page
        return NextJspPage;
    }
}
