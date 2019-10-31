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

   /**
    * @author Gruppe 3
    * 
    * checks whether there is an error occurring in the chosen amount of cakes
    * checks whether there is an error occurring in funds available for purchase and empty basket.
    * redirects to the confirmation page.
    * 
    * @param request html request from FrontController
    * @param response html response from FrontController
    * @return String for next JSP by stringName.jsp
    */ 
    
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "confirmation";
        User user = (User) request.getSession().getAttribute("user");
        String removeLineItemID = request.getParameter("removeCupcakeTopAndBottomID");
        String a = request.getParameter("AmountOf");
        

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
                request.setAttribute("error", "Nothing in shopping cart to buy");
//            NextJspPage = "products";
            } else if (!customer.canBalanceCoverPayment()) {
                request.setAttribute("error", "Balance does not cover the total price in the shopping cart");
//            NextJspPage = "products";
            }
            
        }
        return NextJspPage;
    }
}