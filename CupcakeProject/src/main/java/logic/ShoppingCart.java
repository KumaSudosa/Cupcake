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
     * @version alpha 1.0, first draft
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

    public void removeLineItemFromShoppingCart(int toppingID, int bottomID) {
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
            throw new IllegalArgumentException("Can't find the line-item.");
        }
    }

    public void emptyShoppingCart() {
        this.lineItems = new ArrayList();
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

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

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