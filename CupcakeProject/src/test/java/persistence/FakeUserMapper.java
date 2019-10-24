package persistence;

import persistence.mappers.IUserMapper;
import java.util.ArrayList;
import java.util.HashMap;
import logic.User;

/**
 *
 * @author cahit
 */
public class FakeUserMapper implements IUserMapper {

    ArrayList<HashMap<String, String>> userInfo = new ArrayList();

    public void addUserInfo(HashMap<String, String> userInfo) {
        this.userInfo.add(userInfo);
    }

    @Override
    public ArrayList<HashMap<String, String>> getUserList() {
        return userInfo;
    }

    @Override
    public ArrayList<HashMap<String, String>> getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HashMap<String, String>> getEmail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUser(User user) {

        HashMap<String, String> map = new HashMap();
        map.put("username", user.getUsername());
        map.put("login", user.getPassword());
        map.put("email", user.getEmail());
        map.put("balance", Double.toString(user.getBalance()));
        userInfo.add(map);
    }

}
