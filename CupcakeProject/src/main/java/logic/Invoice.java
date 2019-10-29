package logic;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import persistence.mappers.InvoiceMapperInterface;

/**
 * @author Michael & Marcus
 */
public class Invoice {

    private static ArrayList<Invoice> invoices = new ArrayList();
    private int invoiceID;
    private User user;
    private ArrayList<LineItem> lineItems;
    private String date;
    private static InvoiceMapperInterface invoiceMapper;

    public Invoice(int invoiceID, User user, String date) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.date = date;
        this.lineItems = new ArrayList();
    }
    
    private Invoice(int invoiceID, User user, String date, ArrayList<LineItem> lineItems) {
        this.invoiceID = invoiceID;
        this.user = user;
        this.date = date;
        this.lineItems = lineItems;
    }
    
    public static Invoice convertShoppingCartToNewInvoiceFromUser(Customer customer){
        int invoiceID = invoiceMapper.getNewHighestInvoiceNumber();
        String date = DateTimeFormatter.ofPattern("dd-MM-YYYY").format(LocalDate.now(ZoneId.of("Europe/Copenhagen")));
        ArrayList<LineItem> lineItems = customer.getShoppingCart().getLineItems();
        Invoice newInvoice = new Invoice(invoiceID, customer, date, lineItems);
        customer.payForShoppingCart();
        customer.getShoppingCart().emptyShoppingCart();
        invoiceMapper.uploadInvoice(newInvoice);
        invoices.add(newInvoice);
        return newInvoice;
    }

    public static ArrayList<Invoice> createCustomerInvoicesFromDB(String email) {
        ArrayList<Invoice> result = new ArrayList();
        ArrayList<HashMap<String, String>> list = invoiceMapper.getInvoicesForCustomer(email);
        for (HashMap<String, String> map : list) {
            int invoiceID = Integer.parseInt(map.get("id_invoice"));
            Invoice invoice = findInvoiceInsideListFromID(result, invoiceID);
            if (invoice == null) {
                String date = map.get("date");
                User user = User.getUserFromUserList(email);
                invoice = new Invoice(invoiceID, user, date);
                result.add(invoice);
            }
            int cupcakeToppingID = Integer.parseInt(map.get("id_topping"));
            int cupcakeBottomID = Integer.parseInt(map.get("id_bottom"));
            int amount = Integer.parseInt(map.get("amount"));
            invoice.addLineItemToInvoice(LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount));
        }
        return result;
    }
    
    public static void createAdminInvoicesFromDB() {
        invoices.clear();
        ArrayList<HashMap<String, String>> list = invoiceMapper.getInvoicesForAdmin();
        for (HashMap<String, String> map : list) {
            int invoiceID = Integer.parseInt(map.get("id_invoice"));
            Invoice invoice = findInvoiceInsideListFromID(invoices, invoiceID);
            if (invoice == null) {
                String date = map.get("date");
                String email = map.get("email");
                User user = User.getUserFromUserList(email);
                invoice = new Invoice(invoiceID, user, date);
                invoices.add(invoice);
            }
            int cupcakeToppingID = Integer.parseInt(map.get("id_topping"));
            int cupcakeBottomID = Integer.parseInt(map.get("id_bottom"));
            int amount = Integer.parseInt(map.get("amount"));
            invoice.addLineItemToInvoice(LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount));
        }
    }

    private static Invoice findInvoiceInsideListFromID(List<Invoice> list, int invoiceID) {
        for (Invoice invoice : list) {
            if (invoice.getInvoiceID() == invoiceID) {
                return invoice;
            }
        }
        return null;
    }
    
    public Double getTotalPrice(){
        double totalPrice = 0;
        for (LineItem lineItem : lineItems) {
            totalPrice += lineItem.getSubTotalPrice();
        }
        return totalPrice;
    }
    
    private void addLineItemToInvoice(LineItem lineItem){
        lineItems.add(lineItem);
    }
    
    public static void emptyInvoiceList(){
        invoices.clear();
    }

    public static void setupInvoiceMapper(InvoiceMapperInterface mapper) {
        invoiceMapper = mapper;
    }

    public static ArrayList<Invoice> getInvoices() {
        createAdminInvoicesFromDB();
        return invoices;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getDate() {
        return date;
    }
}