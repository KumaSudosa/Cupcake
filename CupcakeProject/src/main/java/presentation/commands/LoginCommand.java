package presentation.commands;

import java.util.ArrayList;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Invoice;
import logic.User;
import presentation.Command;

/**
 * @author Michael & Marcus
 */

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        
        User.createUsersFromDB();
        
        // Get parameters and set initial nextJspPage String
        String nextJspPage = "";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Logic calls
        try {
            User user = User.LoginUser(email, password);
            request.getSession().setAttribute("user", user);
            
            if (email.equals("Marcus.Marcus@Marcus.dk")) {
                ArrayList<Invoice> invoices = Invoice.getInvoices();
                request.setAttribute("invoices", invoices);
                nextJspPage = "admin";
            } else {
                nextJspPage = "shoppage"; 
            }
            
        } catch (LoginException exception) {
            request.setAttribute("LoginError", exception);
            nextJspPage = "login";
        }
        return nextJspPage;
    }
}