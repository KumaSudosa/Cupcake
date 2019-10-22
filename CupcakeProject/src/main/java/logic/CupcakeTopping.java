package logic;

import java.util.ArrayList;
import java.util.HashMap;
import persistence.ICupcakeMapper;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CupcakeTopping {

    private int cupcakeToppingID;
    private String cupcakeToppingDescription;
    private double priceTopping;
    private static ArrayList<CupcakeTopping> cupcakeToppingsList = new ArrayList();
    private static ICupcakeMapper cupcakeMapper;

    private CupcakeTopping(int cupcakeToppingID, String cupcakeTopping, double priceTopping) {
        this.cupcakeToppingID = cupcakeToppingID;
        this.cupcakeToppingDescription = cupcakeTopping;
        this.priceTopping = priceTopping;
    }

    public static void setupMapper(ICupcakeMapper mapper) {
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

    public static CupcakeTopping getCupcakeToppingFromID(int cupcakeToppingID) {
        for (CupcakeTopping cupcakeTopping : cupcakeToppingsList) {
            if (cupcakeTopping.getCupcakeToppingID() == cupcakeToppingID) {
                return cupcakeTopping;
            }
        }
        throw new IllegalArgumentException("No cupcake topping for cupcake with the given ID");
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
