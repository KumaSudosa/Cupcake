/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.HashMap;
import logic.ShoppingCart;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import persistence.FakeCupcakeMapper;
import persistence.mappers.ICupcakeMapper;

/**
 *
 * @author Michael N. Korsgaard
 */
public class ShoppingCartTest {

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
    public void testConstructer() {
        //act
        ShoppingCart result = new ShoppingCart();

        //assert
        // TODO: add Testing for InvoiceNr when the method to pull highest from mapper has been made.
        int expectedLineItemsSize = 0;
        int expectedAmount = 0;
        int expectedTotalPrice = 0;
        assertEquals(expectedLineItemsSize, result.getLineItems().size());
        assertEquals(expectedAmount, result.getCupcakeAmount());
        assertEquals(expectedTotalPrice, result.getTotalPrice(), 0);
    }

    @Test
    public void testAddLineItemsToEmptyShoppingCart() {
        //arrange
        ShoppingCart result = new ShoppingCart();
        int cupcakeToppingID = 4;
        int cupcakeBottomID = 2;
        int amount = 4;

        //act
        result.addLineItemsToShoppingCart(cupcakeToppingID, cupcakeBottomID, amount);

        //assert
        int expectedLineItemsSize = 1;
        int expectedAmount = 4;
        double expectedCupcakePrice = 11;
        double expectedSubtotalPrice = 44;
        double expectedTotalPrice = 44;
        assertEquals(expectedLineItemsSize, result.getLineItems().size());
        assertEquals(expectedAmount, result.getCupcakeAmount());
        assertEquals(expectedCupcakePrice, result.getLineItems().get(0).getCupcakePrice(), 0);
        assertEquals(expectedSubtotalPrice, result.getLineItems().get(0).getSubTotalPrice(), 0);
        assertEquals(expectedTotalPrice, result.getTotalPrice(), 0);

        //Check we got the right cupcake
        String expectedTopping = "Crispy";
        String expectedBottom = "Vanilla";
        assertTrue(expectedTopping.equals(result.getLineItems().get(0).getCupcakeTopping().getCupcakeToppingDescription()));
        assertEquals(cupcakeToppingID, result.getLineItems().get(0).getCupcakeTopping().getCupcakeToppingID());
        assertTrue(expectedBottom.equals(result.getLineItems().get(0).getCupcakeBottom().getCupcakeBottomDescription()));
        assertEquals(cupcakeBottomID, result.getLineItems().get(0).getCupcakeBottom().getCupcakeBottomID());

    }

    @Test
    public void testAddTwoDifferentLineItemsToShoppingCart() {
        //arrange
        ShoppingCart result = new ShoppingCart();
        int cupcakeToppingIDnr1 = 4;
        int cupcakeBottomIDnr1 = 2;
        int amountNr1 = 4;
        int cupcakeToppingIDnr2 = 6;
        int cupcakeBottomIDnr2 = 2;
        int amountNr2 = 3;

        //act
        result.addLineItemsToShoppingCart(cupcakeToppingIDnr1, cupcakeBottomIDnr1, amountNr1);
        result.addLineItemsToShoppingCart(cupcakeToppingIDnr2, cupcakeBottomIDnr2, amountNr2);

        //assert
        int expectedLineItemsSize = 2;
        int expectedAmount = 7;
        int expectedTotalPrice = 80;
        assertEquals(expectedLineItemsSize, result.getLineItems().size());
        assertEquals(expectedAmount, result.getCupcakeAmount());
        assertEquals(expectedTotalPrice, result.getTotalPrice(), 0);
    }

    @Test
    public void testAddTwoDublicateLineItemsToShoppingCart() {
        //arrange
        ShoppingCart result = new ShoppingCart();
        int cupcakeToppingIDnr1 = 4;
        int cupcakeBottomIDnr1 = 2;
        int amountNr1 = 4;
        int cupcakeToppingIDnr2 = 4;
        int cupcakeBottomIDnr2 = 2;
        int amountNr2 = 3;

        //act
        result.addLineItemsToShoppingCart(cupcakeToppingIDnr1, cupcakeBottomIDnr1, amountNr1);
        result.addLineItemsToShoppingCart(cupcakeToppingIDnr2, cupcakeBottomIDnr2, amountNr2);

        //assert
        int expectedLineItemsSize = 1;
        int expectedAmount = 7;
        int expectedTotalPrice = 77;
        assertEquals(expectedLineItemsSize, result.getLineItems().size());
        assertEquals(expectedAmount, result.getCupcakeAmount());
        assertEquals(expectedTotalPrice, result.getTotalPrice(), 0);
    }

    
    
    @Test
    public void testRemoveLineItemFromShoppingCart(){
    
       ShoppingCart result = new ShoppingCart();
       
        int cupcakeToppingID1 = 4;
        int cupcakeBottomID1 = 2;
        int amount1 = 4;
        int cupcakeToppingID2 = 3;
        int cupcakeBottomID2 = 1;
        int amount2 = 3;
        
        result.addLineItemsToShoppingCart(cupcakeToppingID1, cupcakeBottomID1, amount1);
        result.addLineItemsToShoppingCart(cupcakeToppingID2, cupcakeBottomID2, amount2);
        result.removeLineItemFromShoppingCart(cupcakeToppingID1, cupcakeBottomID1);
        int expectedChoppingCartSize = 1;
        int expectedCupcakeToppingID1 = 4;
        int expectedCupcakeBottomID1 = 2;
        
      
        assertEquals(result.getLineItems().size(), expectedChoppingCartSize);
   
    }
}
