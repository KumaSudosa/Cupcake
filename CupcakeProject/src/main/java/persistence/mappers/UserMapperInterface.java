
package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Customer;

/**
 *
 * @author gruppe 3
 */
public interface UserMapperInterface {
 
public ArrayList<HashMap<String, String>> getUserList(); 

public ArrayList<HashMap<String, String>> getUsername();

public ArrayList<HashMap<String, String>> getEmail();

public void insertUser(Customer user);

public void updateBalance(Customer user);

    
}
