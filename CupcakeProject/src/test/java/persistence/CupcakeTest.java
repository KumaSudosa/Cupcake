/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.HashMap;
import logic.Cupcake;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CupcakeTest {

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
    public void testCupcakeIDConstructor() {
        //arrange
        int cupcakeToppingID = 9;
        int cupcakeBottomID = 4;

        //act
        Cupcake result = new Cupcake(cupcakeToppingID, cupcakeBottomID);

        //assert
        int expectedTotalPrice = 15;
        int expectedToppingPrice = 9;
        int expectedBottomPrice = 6;
        String expectedTopping = "Blue cheese";
        String expectedBottom = "Pistacio";

        assertEquals(expectedBottomPrice, result.getPriceBottom(), 0);
        assertEquals(expectedToppingPrice, result.getPriceTopping(), 0);
        assertEquals(expectedTotalPrice, result.getPrice(), 0);
        assertEquals(cupcakeToppingID, result.getCupcakeToppingID());
        assertEquals(cupcakeBottomID, result.getCupcakeBottomID());
        assertTrue(expectedTopping.equals(result.getCupcakeTopping()));
        assertTrue(expectedBottom.equals(result.getCupcakeBottom()));

    }

}
