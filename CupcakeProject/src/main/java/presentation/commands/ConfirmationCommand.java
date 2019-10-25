package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Invoice;
import logic.User;
import presentation.Command;

/**
 * @author Marcus
 */
public class ConfirmationCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String NextJspPage = "invoice";
        User user = (User) request.getAttribute("user");
        Invoice invoice = Invoice.convertShoppingCartToNewInvoiceFromUser(user);
        
        return NextJspPage;
    }
}