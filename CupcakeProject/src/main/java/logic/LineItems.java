package logic;

public class LineItems {
    
    private Cupcake cupcake;
    private int amount;
    private double subTotalPrice;

    public LineItems(int cupcakeToppingID, int cupcakeBottomID, int amount) {
        this.cupcake = new Cupcake(cupcakeToppingID, cupcakeBottomID);
        this.amount = amount;
        subTotalPrice = amount * cupcake.getPrice();
    }
    
    public void increaseAmount(int increaseAmount){
        this.amount += increaseAmount;
        this.subTotalPrice += increaseAmount * cupcake.getPrice();
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public int getAmount() {
        return amount;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }
    
    
    
    
    
}