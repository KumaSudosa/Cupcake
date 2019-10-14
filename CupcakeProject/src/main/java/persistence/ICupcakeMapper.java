package persistence;

import java.util.ArrayList;
import java.util.HashMap;

 /*
 * @author Gruppe 3
 */

public interface ICupcakeMapper {
    
    public ArrayList<HashMap<String, String>> getCupcakeToppings();
    
    public ArrayList<HashMap<String, String>> getCupcakeBottoms();

    public HashMap<String, String> getCupcakeBottomsFromID(int cupcakeBottomID);

    public HashMap<String, String> getCupcakeToppingsFromID(int cupcakeToppingID);
}