package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import logic.Customer;
import logic.User;
import persistence.mappers.UserMapperInterface;

/**
 *
 * @author cahit
 */
public class FakeUserMapper implements UserMapperInterface {

    ArrayList<HashMap<String, String>> userInfo = new ArrayList();

    public void addUserInfo(HashMap<String, String> userInfo) {
        this.userInfo.add(userInfo);
    }

    @Override
    public ArrayList<HashMap<String, String>> getUserList() {
        return userInfo;
    }

    @Override
    public void insertUser(Customer user) {

        HashMap<String, String> map = new HashMap();
        map.put("username", user.getUsername());
        map.put("login", user.getPassword());
        map.put("email", user.getEmail());
        map.put("balance", Double.toString(user.getBalance()));
        userInfo.add(map);
    }

    @Override
    public void updateBalance(Customer user) {
        for (HashMap<String, String> map : userInfo) {
            if(map.get("email").equals(user.getEmail())){
                map.put("balance", Double.toString(user.getBalance()));
            }
        }
    }

}
