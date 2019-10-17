package logic;

import java.util.ArrayList;

public class ShoppingCart {

    private static ArrayList<ShoppingCart> shoppingCarts = new ArrayList();
    private static int highestInvoiceNr = 101;
    private int invoiceNr;
    private ArrayList<LineItems> lineItems;
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
        for (LineItems lineItem : lineItems) {
            boolean sameTopping = lineItem.getCupcake().getCupcakeToppingID() == cupcakeToppingID;
            boolean sameBottom = lineItem.getCupcake().getCupcakeBottomID() == cupcakeBottomID;

            // If the same type of cupcake is found in a lineItem, increase the amount of the cupcake in that lineItem
            if (sameTopping && sameBottom) {
                lineItem.increaseAmount(amount);
                identicalCupcakeFound = true;
                break;
            }
        }

        // If the same type of cupcake was not found in lineItems, a new lineItem is made
        if (!identicalCupcakeFound) {
            LineItems lineitem = new LineItems(cupcakeToppingID, cupcakeBottomID, amount);
            lineItems.add(lineitem);
        }

        // increase the cupcakeAmount by the new int amount
        cupcakeAmount += amount;

    }

    private void calculateTotalPrice() {
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

    public static int getHighestInvoiceNr() {
        return highestInvoiceNr;
    }

    public int getCupcakeAmount() {
        return cupcakeAmount;
    }
    
    

}
