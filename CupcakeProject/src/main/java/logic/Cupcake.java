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

    
    /**
     * Constructor to be called from flow when cupcake should be added to shoppingCart. 
     * Pulls data from SQL-DB thought CupecakeMapper with matching IDs to the given IDs.
     * 
     * @author Michael N. Korsgaard
     * @version alpha version 1.0, first draft
     * @param cupcakeToppingID int from the selection from jsp mathcing a toppingID in the database.
     * @param cupcakeBottomID int from the selection from jsp mathcing a bottomID in the database.
     */
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

    public double getPriceBottom() {
        return priceBottom;
    }

    public double getPriceTopping() {
        return priceTopping;
    }

    public String getCupcakeTopping() {
        return cupcakeTopping;
    }

    public String getCupcakeBottom() {
        return cupcakeBottom;
    }

    public int getCupcakeToppingID() {
        return cupcakeToppingID;
    }

    public int getCupcakeBottomID() {
        return cupcakeBottomID;
    }
    
    
    
    

}
