package logic;

import java.util.ArrayList;

public class ShoppingCart {

    private static ArrayList<ShoppingCart> shoppingCarts = new ArrayList();
    private static ArrayList<LineItem> lineItems;
    private double totalPrice;

    public ShoppingCart() {
        this.lineItems = new ArrayList();
        this.totalPrice = 0;
    }

    /**
     * @author Michael N. Korsgaard
     * @param cupcakeToppingID int from the selection from jsp mathcing a toppingID in the database.
     * @param cupcakeBottomID int from the selection from jsp mathcing a bottomID in the database.
     * @param amount int the amount of the cupcake that should be added to the shoppingCart
     */
    public void addLineItemsToShoppingCart(int cupcakeToppingID, int cupcakeBottomID, int amount) throws IllegalArgumentException {

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
    }

    /**
     * Remove a specific lineItem in the shoppingCart that matches the given int topppingID and bottomID.
     * 
     * @author Michael N. Korsgaard
     * @param toppingID int ID for topping to be removed.
     * @param bottomID int ID for bottom to be removed.
     * @throws IllegalArgumentException if no lineItem in the shoppingCart has a matching Topping- and Bottom-ID
     */
    public void removeLineItemFromShoppingCart(int toppingID, int bottomID) throws IllegalArgumentException{
        boolean matchingBottom = false;
        boolean matchingTopping = false;
        for (LineItem lineItem : lineItems) {
            matchingBottom = lineItem.getCupcakeBottom().getCupcakeBottomID() == bottomID;
            matchingTopping = lineItem.getCupcakeTopping().getCupcakeToppingID() == toppingID;
            if (matchingBottom && matchingTopping) {
                lineItems.remove(lineItem);
                break;
            }
        }
        if (!matchingBottom || !matchingTopping) {
            throw new IllegalArgumentException("Can't find the lineItem.");
        }
    }

    public void emptyShoppingCart() {
        this.lineItems = new ArrayList();
    }

    /**
     * Find the price for all lineItems in the shoppingCart, and sets the shoppingCart totalPrice.
     */
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

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    /**
     * count the amount of cupcakes in the each LineItem of the shoppingCart, and returns the total.
     * @return int total amount of cupcakes in the shoppingCart
     */
    public int getCupcakeAmount() {
        int totalAmountCupcake = 0;
        for (LineItem lineItem : lineItems) {
            totalAmountCupcake += lineItem.getAmount();
        }
        return totalAmountCupcake;
    }

    public boolean isEmpty() {
        if (this.lineItems.size() > 0) {
            return false;
        }
        return true;
    }
}