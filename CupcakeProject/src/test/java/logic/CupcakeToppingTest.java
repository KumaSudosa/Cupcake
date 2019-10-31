package logic;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import persistence.FakeCupcakeMapper;
import persistence.mappers.CupcakeMapperInterface;

public class CupcakeToppingTest {

    @Before
    public void setup() {
        FakeCupcakeMapper fakeMapper = new FakeCupcakeMapper();
        HashMap<String, String> map = new HashMap();

        String[][] toppings = {
            {"1", "Chocolate", "5"},
            {"2", "Blueberry", "5"},
            {"3", "Rasberry", "5"},
            {"4", "Crispy", "6"},
            {"5", "Strawberry", "6"},
            {"6", "Rum/Raisin", "7"},
            {"7", "Orange", "8"},
            {"8", "Lemon", "8"},
            {"9", "Blue cheese", "9"}
        };

        for (String[] topping : toppings) {
            map = new HashMap();
            map.put("id", topping[0]);
            map.put("topping", topping[1]);
            map.put("price", topping[2]);
            fakeMapper.addToppingInfo(map);
        }
        CupcakeMapperInterface cupcakeMapper = fakeMapper;
        CupcakeTopping.setupMapper(cupcakeMapper);
    }

    @Test
    public void getCupcakeToppingFromIDTest() {
        //arrange
        int ID = 1;

        //act
        CupcakeTopping result = CupcakeTopping.getCupcakeToppingFromID(ID);

        //assert
        String expectedCupcakeDescription = "Chocolate";
        double expectedCupcakePrice = 5;

        assertEquals(ID, result.getCupcakeToppingID());
        assertTrue(expectedCupcakeDescription.equals(result.getCupcakeToppingDescription()));
        assertEquals(expectedCupcakePrice, result.getPriceTopping(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestCupcakeToppingsIdNotFound() {

        int expectedCupcakeToppingsId = 10;

        CupcakeTopping.getCupcakeToppingFromID(expectedCupcakeToppingsId);

    }

}
