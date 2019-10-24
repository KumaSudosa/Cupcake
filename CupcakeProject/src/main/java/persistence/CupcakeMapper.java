package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CupcakeMapper implements ICupcakeMapper {

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

    @Override
    public HashMap<String, String> getCupcakeBottomsFromID(int cupcakeBottomID) {
        ArrayList<HashMap<String, String>> fullList = getCupcakeBottoms();
        for (HashMap<String, String> map : fullList) {
            int mapCupcakeBottomID = Integer.parseInt(map.get("id"));
            if(mapCupcakeBottomID == cupcakeBottomID){
                return map;
            }
        }
        return null;
    }

    @Override
    public HashMap<String, String> getCupcakeToppingsFromID(int cupcakeToppingID) {
        ArrayList<HashMap<String, String>> fullList = getCupcakeToppings();
        for (HashMap<String, String> map : fullList) {
            int mapCupcakeToppingID = Integer.parseInt(map.get("id"));
            if(mapCupcakeToppingID == cupcakeToppingID){
                return map;
            }
        }
        return null;
    }
}