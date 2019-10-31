package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Invoice;
import logic.LineItem;
import persistence.mappers.InvoiceMapperInterface;

/**
 * @author Marcus
 */

public class FakeInvoiceMapper implements InvoiceMapperInterface {
    
     ArrayList<HashMap<String, String>> invoices = new ArrayList();
     
     public void addInvoiceToFakeMapper(HashMap<String, String> invoice) {
        this.invoices.add(invoice);
    }
     
     @Override
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email) {
        ArrayList<HashMap<String, String>> result = new ArrayList();
         for (HashMap<String, String> invoice : invoices) {
             if(invoice.get("email").equals(email)){
                 result.add(invoice);
             }
         }
        return result;
    }
      
    @Override
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin() {
        return invoices;
    }

      
    @Override
    public int getNewHighestInvoiceNumber() {
        int highest = 0;
        for (HashMap<String, String> invoice : invoices) {
            int thisInt = Integer.parseInt(invoice.get("id_invoice"));
            if(highest < thisInt){
                highest = thisInt;
            }
        }
        return highest;
    }

    @Override
    public void uploadInvoice(Invoice invoice) {
        for (LineItem lineItem : invoice.getLineItems()) {
            HashMap<String, String> mapInvoice = new HashMap();
            mapInvoice.put("id_invoice", Integer.toString(invoice.getInvoiceID()));
            mapInvoice.put("email", invoice.getUser().getEmail());
            mapInvoice.put("date", invoice.getDate());
            mapInvoice.put("id_topping", Integer.toString(lineItem.getCupcakeTopping().getCupcakeToppingID()));
            mapInvoice.put("id_bottom", Integer.toString(lineItem.getCupcakeBottom().getCupcakeBottomID()));
            mapInvoice.put("amount", Integer.toString(lineItem.getAmount()));
            invoices.add(mapInvoice);
        }
    }
}