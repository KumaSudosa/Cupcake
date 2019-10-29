package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Invoice;
import persistence.mappers.InvoiceMapperInterface;

/**
 * @author Marcus
 */

public class FakeInvoiceMapper implements InvoiceMapperInterface {
    
     ArrayList<HashMap<String, String>> invoiceInfoCustomer = new ArrayList();
     ArrayList<HashMap<String, String>> invoiceInfoAdmin = new ArrayList();
     
     public void addCustomerInfo(HashMap<String, String> invoiceInfoCustomer) {
        this.invoiceInfoCustomer.add(invoiceInfoCustomer);
    }
     
    public void addAdminInfo (HashMap<String, String> invoiceInfoAdmin) {
        this.invoiceInfoAdmin.add(invoiceInfoAdmin);
    }
     
     @Override
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email) {
        return invoiceInfoCustomer;
    }
      
    @Override
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin() {
        return invoiceInfoAdmin;
    }

      
    @Override
    public int getNewHighestInvoiceNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uploadInvoice(Invoice invoice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}