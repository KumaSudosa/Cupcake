/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.HashMap;
import persistence.ICupcakeMapper;

/**
 *
 * @author Michael N. Korsgaard
 */
public class CupcakeBottom {

    private int cupcakeBottomID;
    private String cupcakeBottomDescription;
    private double priceBottom;
    private static ArrayList<CupcakeBottom> cupcakeBottomsList = new ArrayList();
    private static ICupcakeMapper cupcakeMapper;

    private CupcakeBottom(int cupcakeBottomID, String cupcakeBottom, double priceBottom) {
        this.cupcakeBottomID = cupcakeBottomID;
        this.cupcakeBottomDescription = cupcakeBottom;
        this.priceBottom = priceBottom;
    }

    public static void setupMapper(ICupcakeMapper mapper) {
        cupcakeMapper = mapper;
        setupBottomsFromDB();
    }

    public static void setupBottomsFromDB() {
        cupcakeBottomsList.clear();
        for (HashMap<String, String> cupcakeBottomMap : cupcakeMapper.getCupcakeBottoms()) {
            int cupcakeBottomID = Integer.parseInt(cupcakeBottomMap.get("id"));
            String cupcakeBottomDescription = cupcakeBottomMap.get("bottom");
            double priceBottom = Double.parseDouble(cupcakeBottomMap.get("price"));
            CupcakeBottom cupcakeBottom = new CupcakeBottom(cupcakeBottomID, cupcakeBottomDescription, priceBottom);
            cupcakeBottomsList.add(cupcakeBottom);
        }
    }

    public static CupcakeBottom getCupcakeBottomFromID(int cupcakeBottomID) {
        for (CupcakeBottom cupcakeBottom : cupcakeBottomsList) {
            if (cupcakeBottom.getCupcakeBottomID() == cupcakeBottomID) {
                return cupcakeBottom;
            }
        }
        throw new IllegalArgumentException("No cupcake bottom for cupcake with the given ID");
    }

    public int getCupcakeBottomID() {
        return cupcakeBottomID;
    }

    public String getCupcakeBottomDescription() {
        return cupcakeBottomDescription;
    }

    public double getPriceBottom() {
        return priceBottom;
    }

    public static ArrayList<CupcakeBottom> getCupcakeBottomsList() {
        return cupcakeBottomsList;
    }

}