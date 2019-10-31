package logic;

import java.util.ArrayList;
import java.util.HashMap;
import persistence.mappers.CupcakeMapperInterface;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CupcakeTopping {

    private int cupcakeToppingID;
    private String cupcakeToppingDescription;
    private double priceTopping;
    private static ArrayList<CupcakeTopping> cupcakeToppingsList = new ArrayList();
    private static CupcakeMapperInterface cupcakeMapper;

    private CupcakeTopping(int cupcakeToppingID, String cupcakeTopping, double priceTopping) {
        this.cupcakeToppingID = cupcakeToppingID;
        this.cupcakeToppingDescription = cupcakeTopping;
        this.priceTopping = priceTopping;
    }

    public static void setupMapper(CupcakeMapperInterface mapper) {
        cupcakeMapper = mapper;
        setupCupcakeToppingsFromDB();
    }

    public static void setupCupcakeToppingsFromDB() {
        cupcakeToppingsList.clear();
        for (HashMap<String, String> cupcakeToppingMap : cupcakeMapper.getCupcakeToppings()) {
            int cupcakeToppingID = Integer.parseInt(cupcakeToppingMap.get("id"));
            String cupcakeToppingDescription = cupcakeToppingMap.get("topping");
            double priceTopping = Double.parseDouble(cupcakeToppingMap.get("price"));
            CupcakeTopping cupcakeTopping = new CupcakeTopping(cupcakeToppingID, cupcakeToppingDescription, priceTopping);
            cupcakeToppingsList.add(cupcakeTopping);
        }
    }

    /**
     * @param cupcakeToppingID Finds a cupcake topping based on the specific ID
     * @return returns the cupcake topping that matches the ID
     * @throws Exception if an error is found (if ID doesn't exist) and tells that it doesn't exist and what must be chosen
     */
    public static CupcakeTopping getCupcakeToppingFromID(int cupcakeToppingID) {
        for (CupcakeTopping cupcakeTopping : cupcakeToppingsList) {
            if (cupcakeTopping.getCupcakeToppingID() == cupcakeToppingID) {
                return cupcakeTopping;
            }
        }
        throw new IllegalArgumentException("No cupcake topping for cupcake with the given ID. "
                + "You must choose an ID-number between 1 and " + cupcakeToppingsList.size());
    }

    public int getCupcakeToppingID() {
        return cupcakeToppingID;
    }

    public String getCupcakeToppingDescription() {
        return cupcakeToppingDescription;
    }

    public double getPriceTopping() {
        return priceTopping;
    }

    public static ArrayList<CupcakeTopping> getCupcakeToppingsList() {
        return cupcakeToppingsList;
    }
}