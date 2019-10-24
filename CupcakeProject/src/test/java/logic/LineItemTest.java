/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import logic.LineItem;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import persistence.FakeCupcakeMapper;
import persistence.ICupcakeMapper;

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
        CupcakeTopping.setupMapper(cupcakeMapper);
        CupcakeBottom.setupMapper(cupcakeMapper);
    }

    @Test
    public void testCreateLineItem() {
        //arrange
        int cupcakeToppingID = 4;
        int cupcakeBottomID = 2;
        int amount = 4;

        //act
        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

        //assert
        double expectedSubtotalPrice = 44;
        double expectedPrice = 11;
        String expectedToppingDescription = "Crispy";
        String expectedBottomDescription = "Vanilla";

        assertEquals(amount, result.getAmount());
        assertEquals(expectedSubtotalPrice, result.getSubTotalPrice(), 0);
        assertEquals(expectedPrice, result.getCupcakePrice(), 0);

        //Check we got the right cupcake
        assertTrue(expectedToppingDescription.equals(result.getCupcakeTopping().getCupcakeToppingDescription()));
        assertEquals(cupcakeToppingID, result.getCupcakeTopping().getCupcakeToppingID());
        assertTrue(expectedBottomDescription.equals(result.getCupcakeBottom().getCupcakeBottomDescription()));
        assertEquals(cupcakeBottomID, result.getCupcakeBottom().getCupcakeBottomID());
    }

    @Test
    public void testIncreaseAmount() {

        int bottomID = 2;
        int toppingID = 2;
        int amount = 5;

        LineItem result = LineItem.createLineItem(toppingID, bottomID, amount);

        result.increaseAmount(amount);

        int expectedAmount = 10;

        assertEquals(expectedAmount, result.getAmount());
    }

    
    //Test til amount
    @Test(expected = IllegalArgumentException.class)
    public void negativeTestZeroAmountToppingInput() {

        int cupcakeToppingID = 2;
        int cupcakeBottomID = 2;
        int amount = 0;

        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestMaxOfToppingInputExceeded() {

        int cupcakeToppingID = 2;
        int cupcakeBottomID = 2;
        int amount = 1000;

        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    //Test til cupcakeBottomID
    @Test(expected = IllegalArgumentException.class)
    public void negativeTestCupcakeBottomIDZero() {

        int cupcakeToppingID = 2;
        int cupcakeBottomID = 0;
        int amount = 5;

        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestCupcakeBottomIDSizeOvershoot() {

        int cupcakeToppingID = 2;
        int cupcakeBottomID = 10;
        int amount = 5;

        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    //Tests til cupcakeToppingID
    @Test(expected = IllegalArgumentException.class)
    public void negativeTestCupcakeToppingIDZero() {

        int cupcakeToppingID = 0;
        int cupcakeBottomID = 2;
        int amount = 5;

        LineItem result = LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestCupcakeToppingIDSizeOvershoot() {

        int cupcakeToppingID = 10;
        int cupcakeBottomID = 2;
        int amount = 5;

        LineItem.createLineItem(cupcakeToppingID, cupcakeBottomID, amount);

    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTestIncreaseAmountOverMaxAmount() {

        int bottomID = 2;
        int toppingID = 2;
        int amount = 5;
        int newAmount = 1000;

        LineItem result = LineItem.createLineItem(toppingID, bottomID, amount);

        result.increaseAmount(newAmount);

    }

     @Test(expected = IllegalArgumentException.class)
     public void negativeTestIncreaseNegativeAmount() {

        int bottomID = 2;
        int toppingID = 2;
        int amount = 5;
        int newAmount = -20;

        LineItem result = LineItem.createLineItem(toppingID, bottomID, amount);

        result.increaseAmount(newAmount);

    }
    
}
