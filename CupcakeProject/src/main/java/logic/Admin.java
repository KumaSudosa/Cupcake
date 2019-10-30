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