/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import logic.ShoppingCart;
import logic.User;

/**
 *
 * @author Michael N. Korsgaard
 */
public interface InvoiceMapperInterface {
    
    public int getNewHighestInvoiceNumber();
    
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email);
    
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin();
    
    public void uploadInvoice(ShoppingCart shoppingCart, User user);
    
}
