package logic;

import java.util.ArrayList;

public class ShoppingCart {

    private static ArrayList<ShoppingCart> shoppingCarts = new ArrayList();
    private static int highestInvoiceNr = 101;
    private int invoiceNr;
    private static ArrayList<LineItem> lineItems;
    private double totalPrice;
    private int cupcakeAmount;

    public ShoppingCart() {
        //TODO pull highest inVoiceNr from Mapper, and remove static variable HighestInvoiceNr
        this.invoiceNr = highestInvoiceNr++;
        this.lineItems = new ArrayList();
        this.totalPrice = 0;
        this.cupcakeAmount = 0;
    }

    // TODO: pull down invoices from DB, and make a new private constructer for this process.
    private static void setup() {

    }

    /**
     * @author Michael N. Korsgaard
     * @version alpha 1.0, first draft
     * @param cupcakeToppingID int from the selection from jsp mathcing a toppingID in the database.
     * @param cupcakeBottomID int from the selection from jsp mathcing a bottomID in the database.
     * @param amount int the amount of the cupcake that should be added to the shoppingCart
     */
    public void addLineItemsToShoppingCart(int cupcakeToppingID, int cupcakeBottomID, int amount) {
            
        //Check if an identical cupcake is already in a lineitem
        boolean identicalCupcakeFound = false;
        for (LineItem lineItem : lineItems) {
            boolean sameTopping = lineItem.getCupcakeTopping().getCupcakeToppingID() == cupcakeToppingID;
            boolean sameBottom = lineItem.getCupcakeBottom().getCupcakeBottomID() == cupcakeBottomID;

            // If the same type of cupcake is found in a lineItem, increase the amount of the cupcake in that lineItem
            if (sameTopping && sameBottom) {
                lineItem.increaseAmount(amount);
                identicalCupcakeFound = true;
                break;
            }     
        }

        // If the same type of cupcake was not found in lineItems, a new lineItem is made
        if (!identicalCupcakeFound) {
            LineItem lineitem = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);
            lineItems.add(lineitem);
        }

        // increase the cupcakeAmount by the new int amount
        cupcakeAmount += amount;

    }
    
    public void removeLineItemFromShoppingCart( int toppingID, int bottomID){
    
        for (LineItem lineItem : lineItems) {
            boolean matchingBottom = lineItem.getCupcakeBottom().getCupcakeBottomID() == bottomID;
            boolean matchingTopping = lineItem.getCupcakeTopping().getCupcakeToppingID() == toppingID;
            if(matchingBottom && matchingTopping){
                lineItems.remove(lineItem);
                
                break;
            }
        
            if(!matchingBottom || !matchingTopping){
                
               throw new IllegalArgumentException("Can't find the line-item.");
                
            }
        }
    }
    
    
    private void calculateTotalPrice() {
        int newTotalPrice = 0;
        for (LineItem lineItem : lineItems) {
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

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public static int getHighestInvoiceNr() {
        return highestInvoiceNr;
    }

    public int getCupcakeAmount() {
        return cupcakeAmount;
    }
    
    

}
