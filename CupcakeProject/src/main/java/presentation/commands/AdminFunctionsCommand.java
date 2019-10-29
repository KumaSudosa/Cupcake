package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Admin;
import logic.Customer;
import logic.Invoice;
import presentation.Command;

/**
 *
 * @author andre
 */
public class AdminFunctionsCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String nxtJsp = "";
        Admin admin = (Admin) request.getSession().getAttribute("user");

        String selectedUser = request.getParameter("selectedUser");
        if (request.getParameter("setBalanceButton") != null) {
            try {
                double newBalance = Double.parseDouble(request.getParameter("setBalance"));
                Customer customer = Customer.findCustomerOnEmail(selectedUser);
                admin.changeCustomerBalance(customer, newBalance);
            } catch (NumberFormatException error) {
                request.setAttribute("error", "Please use a valid balance");
            } catch (IllegalArgumentException error) {
                request.setAttribute("error", error.getMessage());
            }
            nxtJsp = "admin";

        } else if (request.getParameter("singleInvoice") != null) {
            try {
                admin.setInvoices(Invoice.createCustomerInvoicesFromDB(selectedUser));
                nxtJsp = "invoice";
            } catch (IllegalArgumentException error) {
                request.setAttribute("error", error.getMessage());
                nxtJsp = "admin";
            }

        } else if (request.getParameter("allInvoice") != null) {
            try {
                admin.setInvoices(Invoice.getInvoices());
                nxtJsp = "invoice";
            } catch (IllegalArgumentException error) {
                request.setAttribute("error", error.getMessage());
                nxtJsp = "admin";
            }
        }
        return nxtJsp;
    }

}
