package logic;

import java.util.ArrayList;
import java.util.HashMap;
import persistence.mappers.CupcakeMapperInterface;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CupcakeBottom {

    private int cupcakeBottomID;
    private String cupcakeBottomDescription;
    private double priceBottom;
    private static ArrayList<CupcakeBottom> cupcakeBottomsList = new ArrayList();
    private static CupcakeMapperInterface cupcakeMapper;

    private CupcakeBottom(String cupcakeBottom, double priceBottom, int cupcakeBottomID) {
        this.cupcakeBottomID = cupcakeBottomID;
        this.cupcakeBottomDescription = cupcakeBottom;
        this.priceBottom = priceBottom;
    }

    public static void setupMapper(CupcakeMapperInterface mapper) {
        cupcakeMapper = mapper;
        setupBottomsFromDB();
    }

    public static void setupBottomsFromDB() {
        cupcakeBottomsList.clear();
        for (HashMap<String, String> cupcakeBottomMap : cupcakeMapper.getCupcakeBottoms()) {
            String cupcakeBottomDescription = cupcakeBottomMap.get("bottom");
            double priceBottom = Double.parseDouble(cupcakeBottomMap.get("price"));
            int cupcakeBottomID = Integer.parseInt(cupcakeBottomMap.get("id"));
            CupcakeBottom cupcakeBottom = new CupcakeBottom(cupcakeBottomDescription, priceBottom, cupcakeBottomID);
            cupcakeBottomsList.add(cupcakeBottom);
        }
    }

    public static CupcakeBottom getCupcakeBottomFromID(int cupcakeBottomID) {
        for (CupcakeBottom cupcakeBottom : cupcakeBottomsList) {
            if (cupcakeBottom.getCupcakeBottomID() == cupcakeBottomID) {
                return cupcakeBottom;
            }
        }
        throw new IllegalArgumentException("No cupcakebottom for cupcake with the given ID. You must choose an ID-number between 1 and " + cupcakeBottomsList.size());
    }
    
    public int getCupcakeBottomID() {
        return cupcakeBottomID;
    }

    public String getCupcakeBottomDescription() {
        return cupcakeBottomDescription;
    }

    public double getPriceBottom() {
        return priceBottom;
    }

    public static ArrayList<CupcakeBottom> getCupcakeBottomsList() {
        return cupcakeBottomsList;
    }
}