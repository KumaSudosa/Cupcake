package logic;

public class LineItem {

    private CupcakeBottom cupcakeBottom;
    private CupcakeTopping cupcakeTopping;
    private int amount;
    private double cupcakePrice;
    private double subTotalPrice;
    private static final int MAX_AMOUNT = 1000;
    private static final int MIN_AMOUNT = 1;

    private LineItem(int cupcakeToppingID, int cupcakeBottomID, int amount) {
        this.cupcakeBottom = CupcakeBottom.getCupcakeBottomFromID(cupcakeBottomID);
        this.cupcakeTopping = CupcakeTopping.getCupcakeToppingFromID(cupcakeToppingID);
        this.amount = amount;
        this.cupcakePrice = cupcakeBottom.getPriceBottom() + cupcakeTopping.getPriceTopping();
        this.subTotalPrice = amount * cupcakePrice;
    }
    
    /**
     * This method creates line items.
     * It makes an integer variable and sets it equal to the size of all cupcake bottoms (in this programme = 5) and cupcake toppings (in this programme = 9).
     * @param cupcakeToppingID throws an error if ID is below 1 or higher than the size (higher than 9).
     * @param cupcakeBottomID throws an error if ID is below 1 or higher than the size (higher than 5).
     * @param amount throws an error if the amount of chosen cupcakes is below 1 or higher than 999
     * @return if no errors are encountered the method then creates a lineitem with the cupcakebottom ID, cupcaketopping ID and the amount chosen. 
     * @throws IllegalArgumentException 
     */
    public static LineItem createLineItem(int cupcakeToppingID, int cupcakeBottomID, int amount) throws IllegalArgumentException {

        int cupcakeBottomSize = CupcakeBottom.getCupcakeBottomsList().size();
        int cupcakeToppingSize = CupcakeTopping.getCupcakeToppingsList().size();

        //Check if amount is within acceptable parameters    
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("Chosen amount is less than 1. Must be at least 1.");
        }
        if (amount >= MAX_AMOUNT) {
            throw new IllegalArgumentException("Chosen amount exceeds max-limit for amounts. Must be less than 1000");
        }

        // Check if ID matches cupcakeBottomID
        if (cupcakeBottomID < 1 || cupcakeBottomID > cupcakeBottomSize) {
            throw new IllegalArgumentException("You must select ID number betweeen 1 and " + cupcakeBottomSize);
        }

        //Check if ID matches cupcakeToppingID
        if (cupcakeToppingID < 1 || cupcakeToppingID > cupcakeToppingSize) {
            throw new IllegalArgumentException("You must select ID number betweeen 1 and " + cupcakeToppingSize);
        }
        
        LineItem result = new LineItem(cupcakeToppingID, cupcakeBottomID, amount);
        return result;
    }

    /**
     * Method increase the amount of already defined / chosen cupcakes.
     * @param increaseAmount
     * @throws IllegalArgumentException if already chosen amount plus the "increaseAmount" exceeds the maximum possible amount (999) it throws an error
     * likewise if "increaseAmount" is below 0 (in minus) is throws an error as well.
     * if no errors are encountered, it adds the "increaseAmount" to the already chosen amount, and sets the "subTotalPrice" as the "increaseAmount" chosen * the price of the chosen cupcake
     */
    public void increaseAmount(int increaseAmount) throws IllegalArgumentException {

        if(this.amount + increaseAmount > MAX_AMOUNT){
            throw new IllegalArgumentException("Amount exceeds max amount.");
        }
        
        if(increaseAmount < 0){
            throw new IllegalArgumentException("Chosen amount less than 0.");
        }
        
        this.amount += increaseAmount;
        this.subTotalPrice += increaseAmount * cupcakePrice;
    }

    public int getAmount() {
        return amount;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public CupcakeBottom getCupcakeBottom() {
        return cupcakeBottom;
    }

    public CupcakeTopping getCupcakeTopping() {
        return cupcakeTopping;
    }

    public double getCupcakePrice() {
        return cupcakePrice;
    }

    @Override
    public String toString() {
        return cupcakeBottom.getCupcakeBottomDescription() + " with " + cupcakeTopping.getCupcakeToppingDescription();
    }
}