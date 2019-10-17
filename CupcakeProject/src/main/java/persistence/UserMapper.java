
package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.User;

/**
 *
 * @author Gruppe 3
 */
public class UserMapper implements IUserMapper {
    
   
    public ArrayList<HashMap<String, String>> getUserList() {
        ArrayList<HashMap<String, String>> users = new ArrayList();

        String sql = "SELECT * FROM cupcakeshop.users";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("username", rs.getString("username"));
                map.put("login", rs.getString("login"));
                map.put("email", rs.getString("email"));
                map.put("balance", rs.getString("balance"));
                users.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public ArrayList<HashMap<String, String>> getUsername() {

            ArrayList<HashMap<String, String>> usernames = new ArrayList();

        String sql = "SELECT * FROM cupcakeshop.users";
        
        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("username", rs.getString("username"));
               
                usernames.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usernames;    
 }


    @Override
    public ArrayList<HashMap<String, String>> getEmail() {

    ArrayList<HashMap<String, String>> emails = new ArrayList();

        String sql = "SELECT * FROM cupcakeshop.users";
        
        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("email", rs.getString("email"));
               
                emails.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emails;    
}

    @Override
    public void insertUser(User user) {

     String sql = "INSERT INTO users (username, login, email, balance)"
             + "VALUES("+user.getUsername()+", "+user.getPassword()+", "
             + user.getEmail()+", " +user.getBalance()+ ")";
     
        try {
            DB.getConnection().prepareStatement(sql).executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
     

    }

  
    
}
