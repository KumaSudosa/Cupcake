package logic;

/**
 * @author Andreas
 */
public class Customer extends User {

    private double balance;
    private static ShoppingCart shoppingCart;

    public Customer(String username, String password, String email, String role, double balance) {
        super(username, password, email, role);
        this.balance = balance;
    }

    public void payForShoppingCart() {
        if (this.canBalanceCoverPayment()) {
            double payment = getShoppingCart().getTotalPrice();
            this.balance -= payment;
            User.getUserMapper().updateBalance(this);
        } else {
            throw new IllegalArgumentException("There is an error with the shopping cart");
        }
    }

    public boolean canBalanceCoverPayment() {
        if (this.shoppingCart.getTotalPrice() > this.balance) {
            return false;
        }
        return true;
    }
    
    public static Customer findCustomerOnEmail(String email) throws IllegalArgumentException {
        User user = User.getUserFromUserList(email);
        if(User.isUserCustomer(user)) {
            Customer customer = (Customer) user;
            return customer;
        }
        throw new IllegalArgumentException("Email did not belong to a customer");
    }

    public double getBalance() {
        return balance;
    }

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public static void setShoppingCart(ShoppingCart shoppingCart) {
        Customer.shoppingCart = shoppingCart;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}