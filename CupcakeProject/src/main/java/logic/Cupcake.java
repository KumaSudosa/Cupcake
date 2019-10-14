package logic;

import java.util.HashMap;
import persistence.ICupcakeMapper;

public class Cupcake {

    private double price;
    private double priceBottom;
    private double priceTopping;
    private String cupcakeTopping;
    private String cupcakeBottom;
    private int cupcakeToppingID;
    private int cupcakeBottomID;
    private static ICupcakeMapper cupcakeMapper;

    public static void setupCupcakeMapper(ICupcakeMapper cupcakeMapper) {
        Cupcake.cupcakeMapper = cupcakeMapper;
    }

    public Cupcake(int cupcakeToppingID, int cupcakeBottomID) {
        this.cupcakeToppingID = cupcakeToppingID;
        this.cupcakeBottomID = cupcakeBottomID;
        HashMap<String, String> toppingInfo = cupcakeMapper.getCupcakeToppingsFromID(cupcakeToppingID);
        HashMap<String, String> bottomInfo = cupcakeMapper.getCupcakeBottomsFromID(cupcakeBottomID);
        this.cupcakeTopping = toppingInfo.get("topping");
        this.priceTopping = Integer.parseInt(toppingInfo.get("price"));
        this.cupcakeBottom = bottomInfo.get("bottom");
        this.priceBottom = Integer.parseInt(bottomInfo.get("price"));
        this.price = this.priceTopping + this.priceBottom;

    }

    public double getPrice() {
        return price;
    }

}
