package logic;

public class LineItem {

    private CupcakeBottom cupcakeBottom;
    private CupcakeTopping cupcakeTopping;
    private int amount;
    private double cupcakePrice;
    private double subTotalPrice;

    public LineItem(int cupcakeToppingID, int cupcakeBottomID, int amount) {
        this.cupcakeBottom = CupcakeBottom.getCupcakeBottomFromID(cupcakeBottomID);
        this.cupcakeTopping = CupcakeTopping.getCupcakeToppingFromID(cupcakeToppingID);
        this.amount = amount;
        this.cupcakePrice = cupcakeBottom.getPriceBottom() + cupcakeTopping.getPriceTopping();
        this.subTotalPrice = amount * cupcakePrice;
    }

    public void increaseAmount(int increaseAmount) {
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
}