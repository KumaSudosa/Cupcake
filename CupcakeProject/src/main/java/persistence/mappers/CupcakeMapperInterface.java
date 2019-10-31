package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * @author Gruppe 3
 */
public interface CupcakeMapperInterface {

    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * topping description, 'topping'<br>
     * price, 'price'<br>
     * toppingID, 'id'
     *
     * @return HashMap with every Topping, as stored above
     */
    public ArrayList<HashMap<String, String>> getCupcakeToppings();

    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * bottom description, 'bottom'<br>
     * price, 'price'<br>
     * bottomID, 'id'
     *
     * @return HashMap with every Topping, as stored above
     */
    public ArrayList<HashMap<String, String>> getCupcakeBottoms();
}
