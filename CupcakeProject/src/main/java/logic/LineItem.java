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

    public static LineItem createLineItem(int cupcakeToppingID, int cupcakeBottomID, int amount) throws IllegalArgumentException {

        int cupcakeBottomSize = CupcakeBottom.getCupcakeBottomsList().size();
        int cupcakeToppingSize = CupcakeTopping.getCupcakeToppingsList().size();

        //Check amount is within acceptable parameters    
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("Chosen amount is less than 1. Must be at least 1.");
        }
        if (amount >= MAX_AMOUNT) {
            throw new IllegalArgumentException("Chosen amount exceeds max-limit for amounts. Must be less than 1000");
        }

        // Check ID matches cupcakeBottomID
        if (cupcakeBottomID < 1 || cupcakeBottomID > cupcakeBottomSize) {
            throw new IllegalArgumentException("You must select ID number betweeen 1 and " + cupcakeBottomSize);
        }

        //Check ID matches cupcakeToppingID
        if (cupcakeToppingID < 1 || cupcakeToppingID > cupcakeToppingSize) {
            throw new IllegalArgumentException("You must select ID number betweeen 1 and " + cupcakeToppingSize);
        }
        
        LineItem result = new LineItem(cupcakeToppingID, cupcakeBottomID, amount);
        return result;
    
    
    }

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