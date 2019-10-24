/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Invoice;

/**
 *
 * @author Michael N. Korsgaard
 */
public interface InvoiceMapperInterface {
    
    public int getNewHighestInvoiceNumber();
    
    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * invoiceID, 'id_invoice'<br>
     * date, 'date'<br>
     * toppingID, 'id_topping'<br>
     * bottomID, 'id_bottom'<br>
     * cupcakeAmount, 'amount'<br>
     * 
     */
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email);
    
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin();
    
    public void uploadInvoice(Invoice invoice);
    
}
