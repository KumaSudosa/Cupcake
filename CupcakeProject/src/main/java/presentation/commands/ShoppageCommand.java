package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Customer;
import logic.User;
import presentation.Command;

/**
 * @author Marcus
 */
public class ShoppageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        // Get parameters and set initial nextJspPage String
        User user = (User) request.getSession().getAttribute("user");
        String nextJspPage = "products";
        String toppingString = request.getParameter("toppingchoice");
        String bottomString = request.getParameter("bottomchoice");
        String amountString = request.getParameter("AmountOf");

        // Logic calls
        if (User.isUserCustomer(user)) {
            Customer customer = (Customer) user;
            if (toppingString != null && bottomString != null && amountString != null) {
                int topping = Integer.parseInt(toppingString);
                int bottom = Integer.parseInt(bottomString);
                int amount = Integer.parseInt(amountString);
                try {
                    customer.getShoppingCart().addLineItemsToShoppingCart(topping, bottom, amount);
                } // Set Attributes and go to next Page
                catch (IllegalArgumentException ex) {
                    request.setAttribute("error", ex.getMessage());
                }
            }
        }

        return nextJspPage;
    }
}
