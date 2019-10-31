package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Invoice;

/**
 * @author Michael N. Korsgaard
 */
public interface InvoiceMapperInterface {

    /**
     * Get the highest ordernumber +1 from DB
     *
     * @return Highest ordernumber in DB, +1, so we get a newest highest orderNumber
     */
    public int getNewHighestInvoiceNumber();

    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * invoiceID, 'id_invoice'<br>
     * date, 'date'<br>
     * toppingID, 'id_topping'<br>
     * bottomID, 'id_bottom'<br>
     * cupcakeAmount, 'amount'
     *
     * @return HashMap with every LineItem for the invoices for the emails, as stored above
     */
    public ArrayList<HashMap<String, String>> getInvoicesForCustomer(String email);

    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * invoiceID, 'id_invoice'<br>
     * email, 'email'<br>
     * date, 'date'<br>
     * toppingID, 'id_topping'<br>
     * bottomID, 'id_bottom'<br>
     * cupcakeAmount, 'amount'
     *
     * @return HashMap with every LineItem for all invoices, as stored above
     */
    public ArrayList<HashMap<String, String>> getInvoicesForAdmin();

    /**
     * Inserts an invoice to the DB by first uploading the invoice, and then each lineItems in Invoice after.
     *
     * @param invoice invoice object to be uploaded
     */
    public void uploadInvoice(Invoice invoice);
}
