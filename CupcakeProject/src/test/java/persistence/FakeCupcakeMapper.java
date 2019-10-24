package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import persistence.mappers.CupcakeMapperInterface;

public class FakeCupcakeMapper implements CupcakeMapperInterface {

    ArrayList<HashMap<String, String>> bottomInfo = new ArrayList();
    ArrayList<HashMap<String, String>> toppingInfo = new ArrayList();

    @Override
    public ArrayList<HashMap<String, String>> getCupcakeToppings() {
        return toppingInfo;
    }

    @Override
    public ArrayList<HashMap<String, String>> getCupcakeBottoms() {
        return bottomInfo;
    }

    @Override
    public HashMap<String, String> getCupcakeBottomsFromID(int cupcakeBottomID) {
        ArrayList<HashMap<String, String>> fullList = getCupcakeBottoms();
        for (HashMap<String, String> map : fullList) {
            int mapCupcakeBottomID = Integer.parseInt(map.get("id"));
            if (mapCupcakeBottomID == cupcakeBottomID) {
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
            if (mapCupcakeToppingID == cupcakeToppingID) {
                return map;
            }
        }
        return null;
    }

    public void setBottomInfo(ArrayList<HashMap<String, String>> bottomInfo) {
        this.bottomInfo = bottomInfo;
    }

    public void setToppingInfo(ArrayList<HashMap<String, String>> toppingInfo) {
        this.toppingInfo = toppingInfo;
    }

    public void addBottomInfo(HashMap<String, String> bottomInfoMap) {
        this.bottomInfo.add(bottomInfoMap);
    }

    public void addToppingInfo(HashMap<String, String> toppingInfoMap) {
        this.toppingInfo.add(toppingInfoMap);
    }

}
