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
    public void testConnection() throws SQLException{
        //arrange
        Connection result;
        
        //act
        result = DB.getConnection();
        
        //assert
        assertNotNull(result);
        assertTrue(!result.isClosed());
    }
    
    
    @Test
    public void testDatabaseInfo() throws SQLException {
        //arrange
        String SQL = "SELECT username FROM cupcakeshop.users";
        ArrayList<String> result = new ArrayList();
        
        //act
        ResultSet rs = DB.getConnection().prepareStatement(SQL).executeQuery();
        while(rs.next()){
            result.add(rs.getString("username"));
        }
        
        //assert
        assertTrue(result.contains("Marcus"));
    }
    
    
    @Test
    public void insertIntoDatabase() throws SQLException {
        //arrange
        String SQL = ("INSERT INTO cupcakeshop.users (username, login, email, balance)"
                   + " VALUES ('Andreas', 'Andreas123', 'Andreas.Andreas@Andreas.dk', 16.0)");
        
        //act
        int results = DB.getConnection().prepareStatement(SQL).executeUpdate(); 
        
        //assert
        assertEquals(1, results);
        
        //deleting test user from database, to use test again later
        String SQL1 = "DELETE FROM cupcakeshop.users WHERE username = 'Andreas'";
        DB.getConnection().prepareStatement(SQL1).executeUpdate(); 
    }
}