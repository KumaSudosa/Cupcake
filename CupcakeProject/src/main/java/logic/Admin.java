package logic;

import java.util.ArrayList;

/**
 *
 * @author Andreas
 */

public class Admin extends User{
    
    private ArrayList<Invoice> invoices;
    
    public Admin(String username, String password, String email, String role) {
        super(username, password, email, role);
        this.invoices = new ArrayList();
    }
    
    /**
     * @param user the methods takes a user variable (to identify a specific user)
     * @param newBalance and takes a new balance that will be given to the user
     */
    
    public void changeCustomerBalance(Customer user, double newBalance) {
        user.setBalance(newBalance);
        User.getUserMapper().updateBalance(user);
    }
    
    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }
}