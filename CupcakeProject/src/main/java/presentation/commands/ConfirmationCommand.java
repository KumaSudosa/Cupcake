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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "invoice";
        Customer customer = (Customer) request.getSession().getAttribute("user");
        
        // Logic calls
        Invoice invoice = Invoice.convertShoppingCartToNewInvoiceFromUser(customer);
        
        // Set Attributes and go to next Page
        return NextJspPage;
    }
}