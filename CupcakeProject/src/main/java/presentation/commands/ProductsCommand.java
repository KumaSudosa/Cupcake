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
public class ProductsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "confirmation";
        User user = (User) request.getSession().getAttribute("user");
        String removeLineItemID = request.getParameter("removeCupcakeTopAndBottomID");

        // Logic calls
        if (User.isUserCustomer(user)) {
            Customer customer = (Customer) user;
            
            if (removeLineItemID != null) {
                String[] lineItemIDs = removeLineItemID.split(":");
                int removeBottomID = Integer.parseInt(lineItemIDs[0]);
                int removeToppingID = Integer.parseInt(lineItemIDs[1]);
                customer.getShoppingCart().removeLineItemFromShoppingCart(removeToppingID, removeBottomID);
            }

            // Set Attributes and go to next Page
            if (customer.getShoppingCart().isEmpty()) {
                request.setAttribute("error", "Nothing in shoppingcart to buy");
//            NextJspPage = "products";
            } else if (!customer.canBalanceCoverPayment()) {
                request.setAttribute("error", "Balance do not cover the the total price in the shoppingcart");
//            NextJspPage = "products";
            }
        }

        return NextJspPage;
    }
}
