package logic;

import java.util.ArrayList;

public class ShoppingCart {
    
    private static ArrayList<ShoppingCart> shoppingCarts = new ArrayList();
    private static int highestInvoiceNr = 101;
    private int invoiceNr;
    private ArrayList<LineItems> lineItems;
    private double totalPrice;
    

    public ShoppingCart() {
        //TODO pull highest inVoiceNr from Mapper, and remove static variable HighestInvoiceNr
        this.invoiceNr = highestInvoiceNr++;
        this.lineItems = new ArrayList();
        this.totalPrice = 0;
    }
    
    // TODO: pull down invoices from DB, and make a new private constructer for this process.
    private static void setup(){
        
    }
    
    public void addLineItemsToShoopingCart(int cupcakeToppingID, int cupcakeBottomID, int amount){
        LineItems lineitem = new LineItems(cupcakeToppingID, cupcakeBottomID, amount);
        lineItems.add(lineitem);
    }
    
    private void calculateTotalPrice(){
        int newTotalPrice = 0;
        for (LineItems lineItem : lineItems) {
            newTotalPrice += lineItem.getSubTotalPrice();
        }
        this.totalPrice = newTotalPrice;
    }

    public static ArrayList<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public int getInvoiceNr() {
        return invoiceNr;
    }

    public ArrayList<LineItems> getLineItems() {
        return lineItems;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }
    
    
    
    
    
}