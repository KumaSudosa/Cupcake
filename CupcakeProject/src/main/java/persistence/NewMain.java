package persistence;
public class NewMain {

    public static void main(String[] args) {
        
        CupcakeMapper cm = new CupcakeMapper();
        System.out.println(cm.getCupcakeBottoms());
        System.out.println();
        System.out.println(cm.getCupcakeToppings());
    }
}