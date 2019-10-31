package persistence.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DB;

public class CupcakeMapper implements CupcakeMapperInterface {

    @Override
    public ArrayList<HashMap<String, String>> getCupcakeBottoms() {
        ArrayList<HashMap<String, String>> bottoms = new ArrayList();

        String sql = "SELECT * FROM cupcakeshop.bottoms";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("bottom", rs.getString("bottom"));
                map.put("price", rs.getString("price"));
                map.put("id", rs.getString("id"));
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

        String sql = "SELECT * FROM cupcakeshop.toppings";

        try {
            ResultSet rs = DB.getConnection().prepareStatement(sql).executeQuery();
            while (rs.next()) {
                HashMap<String, String> map = new HashMap();
                map.put("id", rs.getString("id"));
                map.put("topping", rs.getString("topping"));
                map.put("price", rs.getString("price"));
                toppings.add(map);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CupcakeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toppings;
    }
}
