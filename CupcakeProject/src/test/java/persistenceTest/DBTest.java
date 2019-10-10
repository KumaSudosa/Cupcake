package persistenceTest;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.DB;

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
}