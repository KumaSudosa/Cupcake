/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import persistence.mappers.InvoiceMapperInterface;

/**
 *
 * @author Michael N. Korsgaard
 */
public class Invoice {

    private static ArrayList<Invoice> invoices;
    private int invoiceID;
    private User user;
    private static ArrayList<LineItem> lineItems;
    private String date;
    private static InvoiceMapperInterface invoiceMapper;

    public Invoice(int invoiceID, User user, String date) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.date = date;
        this.lineItems = new ArrayList();
    }

    public static ArrayList<Invoice> createCustomerInvoicesFromDB(String email) {
        ArrayList<Invoice> result = new ArrayList();
        ArrayList<HashMap<String, String>> list = invoiceMapper.getInvoicesForCustomer(email);
        for (HashMap<String, String> map : list) {
            int invoiceID = Integer.parseInt(map.get("id_invoice"));
            Invoice invoice = findInvoiceInsideListFromID(result, invoiceID);
            if (invoice == null) {
                User user = User.getUserFromUserList(email);
                invoice = new Invoice(invoiceID, user, email);
                result.add(invoice);
            }
            int cupcakeToppingID = Integer.parseInt(map.get("id_topping"));
            int cupcakeBottomID = Integer.parseInt(map.get("id_bottom"));
            int amount = Integer.parseInt(map.get("amount"));
            invoice.addLineItemToInvoice(LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount));
        }
        return result;
    }

    private static Invoice findInvoiceInsideListFromID(List<Invoice> list, int invoiceID) {
        for (Invoice invoice : list) {
            if (invoice.getInvoiceID() == invoiceID) {
                return invoice;
            }
        }
        return null;
    }
    
    private void addLineItemToInvoice(LineItem lineItem){
        lineItems.add(lineItem);
    }

    public static void setupInvoiceMapper(InvoiceMapperInterface mapper) {
        invoiceMapper = mapper;
    }

    public static ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public User getUser() {
        return user;
    }

    public static ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getDate() {
        return date;
    }

}
