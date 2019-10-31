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
