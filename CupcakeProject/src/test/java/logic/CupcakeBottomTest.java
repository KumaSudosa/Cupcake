package logic;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import persistence.FakeCupcakeMapper;
import static org.junit.Assert.*;
import persistence.mappers.CupcakeMapperInterface;

public class CupcakeBottomTest {

    @Before
    public void setup() {
        FakeCupcakeMapper fakeMapper = new FakeCupcakeMapper();
        HashMap<String, String> map = new HashMap();

        String[][] bottoms = {
            {"1", "Chocolate", "5"},
            {"2", "Vanilla", "5"},
            {"3", "Nutmeg", "5"},
            {"4", "Pistachio", "6"},
            {"5", "Almond", "7"}
        };

        for (String[] bottom : bottoms) {
            map = new HashMap();
            map.put("id", bottom[0]);
            map.put("bottom", bottom[1]);
            map.put("price", bottom[2]);
            fakeMapper.addBottomInfo(map);
        }
        CupcakeMapperInterface cupcakeMapper = fakeMapper;
        CupcakeBottom.setupMapper(cupcakeMapper);
    }

    @Test
    public void getCupcakeBottomFromIDTest() {
        //arrange
        int ID = 1;

        //act
        CupcakeBottom result = CupcakeBottom.getCupcakeBottomFromID(ID);

        //assert
        String expectedCupcakeDescription = "Chocolate";
        double expectedCupcakePrice = 5;

        assertEquals(ID, result.getCupcakeBottomID());
        assertTrue(expectedCupcakeDescription.equals(result.getCupcakeBottomDescription()));
        assertEquals(expectedCupcakePrice, result.getPriceBottom(), 0);
    }

    @Test(expected = IllegalArgumentException.class)

    public void negativeTestCupcakeBottomIdNotFound() {

        int expectedCupcakeBottomId = 10;

        CupcakeBottom.getCupcakeBottomFromID(expectedCupcakeBottomId);
    }

}
