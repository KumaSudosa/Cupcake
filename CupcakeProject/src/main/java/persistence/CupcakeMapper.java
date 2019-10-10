package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CupcakeMapper implements ICupcakeMapper {

    @Override
    public ArrayList<HashMap<String, String>> getUsers() {
        ArrayList<HashMap<String, String>> users = new ArrayList();

        String sql = "SELECT * FROM cupcakeproject.users";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("username", rs.getString("username"));
                map.put("password", rs.getString("password"));
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
    public ArrayList<HashMap<String, String>> getCupcakeBottoms() {
        ArrayList<HashMap<String, String>> bottoms = new ArrayList();

        String sql = "SELECT * FROM cupcakeproject.bottoms";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("username", rs.getString("username"));
                map.put("password", rs.getString("password"));
                map.put("email", rs.getString("email"));
                map.put("balance", rs.getString("balance"));
                bottoms.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bottoms;
    }
    
    
    @Override
    public ArrayList<HashMap<String, String>> getCupcakeToppings() {
        ArrayList<HashMap<String, String>> toppings = new ArrayList();

        String sql = "SELECT * FROM cupcakeproject.toppings";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("username", rs.getString("username"));
                map.put("password", rs.getString("password"));
                map.put("email", rs.getString("email"));
                map.put("balance", rs.getString("balance"));
                toppings.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toppings;
    }
}