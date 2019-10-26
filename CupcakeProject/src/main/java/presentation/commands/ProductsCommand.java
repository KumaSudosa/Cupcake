package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;
import presentation.Command;

/**
 * @author Marcus
 */
public class ProductsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String NextJspPage = "confirmation";
        User user = (User) request.getSession().getAttribute("user");
        String removeLineItemID = request.getParameter("removeCupcakeTopAndBottomID");
        if(removeLineItemID != null){
            String[] lineItemIDs = removeLineItemID.split(":");
            int removeBottomID = Integer.parseInt(lineItemIDs[0]);
            int removeToppingID = Integer.parseInt(lineItemIDs[1]);
            user.getShoppingCart().removeLineItemFromShoppingCart(removeToppingID, removeBottomID);
        }
//        if(user.getShoppingCart().isEmpty()){
//            request.setAttribute("error", "Nothing in shoppingcart to buy");
//            NextJspPage = "products";
//        } else 
            if (!user.canBalanceCoverPayment()) {
            request.setAttribute("error", "Balance do not cover the the total price in the shoppingcart");
            NextJspPage = "products";
        }

        return NextJspPage;
    }
}
