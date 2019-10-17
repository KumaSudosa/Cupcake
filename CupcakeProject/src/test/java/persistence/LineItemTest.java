/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.HashMap;
import logic.Cupcake;
import logic.LineItems;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Michael N. Korsgaard
 */
public class LineItemTest {

    @Before
    public void setup() {
        FakeCupcakeMapper fakeMapper = new FakeCupcakeMapper();
        HashMap<String, String> map = new HashMap();

        String[][] bottoms = {
            {"1", "Chocolate", "5"},
            {"2", "Vanilla", "5"},
            {"3", "Nutmeg", "5"},
            {"4", "Pistacio", "6"},
            {"5", "Almond", "7"}
        };

        for (String[] bottom : bottoms) {
            map = new HashMap();
            map.put("id", bottom[0]);
            map.put("bottom", bottom[1]);
            map.put("price", bottom[2]);
            fakeMapper.addBottomInfo(map);
        }

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

        ICupcakeMapper cupcakeMapper = fakeMapper;
        Cupcake.setupCupcakeMapper(cupcakeMapper);
    }

    @Test
    public void testConstructer() {
        //arrange
        int cupcakeToppingID = 4;
        int cupcakeBottomID = 2;
        int amount = 4;

        //act
        LineItems result = new LineItems(cupcakeToppingID, cupcakeBottomID, amount);

        //assert
        double expectedSubtotalPrice = 44;
        double expectedPrice = 11;
        String expectedTopping = "Crispy";
        String expectedBottom = "Vanilla";

        assertEquals(amount, result.getAmount());
        assertEquals(expectedSubtotalPrice, result.getSubTotalPrice(), 0);

        //Check we got the right cupcake
        assertTrue(expectedTopping.equals(result.getCupcake().getCupcakeTopping()));
        assertEquals(cupcakeToppingID, result.getCupcake().getCupcakeToppingID());
        assertTrue(expectedBottom.equals(result.getCupcake().getCupcakeBottom()));
        assertEquals(cupcakeBottomID, result.getCupcake().getCupcakeBottomID());
        assertEquals(expectedPrice, result.getCupcake().getPrice(), 0);

    }

}
