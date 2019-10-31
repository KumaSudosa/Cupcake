package persistence.mappers;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Customer;

/**
 *
 * @author gruppe 3
 */
public interface UserMapperInterface {

    /**
     * <b>Infomation is stored as follows:<br>
     * <i>information, hashmap-key</i></b><br><br>
     * username, 'username'<br>
     * password, 'login'<br>
     * email, 'email'<br>
     * balance, 'balance'<br>
     * user-subclass type (a = admin, c = customer), 'role'
     *
     * @return HashMap with every User, as stored above
     */
    public ArrayList<HashMap<String, String>> getUserList();

    /**
     * Upon successful registration, this uploads the new Customer to the DB
     *
     * @param user Customer to be uploaded to DB
     */
    public void insertUser(Customer user);

    /**
     * Upon payment, upload the Customer's updated balance to the DB
     *
     * @param user Customer with balance to be updated in DB
     */
    public void updateBalance(Customer user);

}
