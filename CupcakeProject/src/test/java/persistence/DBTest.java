package persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @Gruppe 3
 */
public class DBTest {

    @Test
    public void testConnection() throws SQLException {
        //arrange
        Connection result;

        //act
        result = DB.getConnection();

        //assert
        assertNotNull(result);
        assertTrue(!result.isClosed());
    }

    @Test
    public void testSelectFromDatabaseInfo() throws SQLException {
        //arrange
        String SQL = "SELECT testName FROM cupcakeshop.test";
        ArrayList<String> result = new ArrayList();

        //act
        ResultSet rs = DB.getConnection().prepareStatement(SQL).executeQuery();
        while (rs.next()) {
            result.add(rs.getString("testName"));
        }

        //assert
        int expectedSize = 3;
        assertEquals(expectedSize, result.size());
        assertTrue(result.contains("Test1"));
        assertTrue(result.contains("TestB"));
        assertTrue(result.contains("TestThree"));
    }

}
