package logic;

import javax.security.auth.login.LoginException;
import static logic.User.createUsersFromDB;
import static logic.User.getUserListArray;
import persistence.mappers.UserMapperInterface;

/**
 *
 * @author andre
 */
public class Customer extends User {

    private double balance;
    private static ShoppingCart shoppingCart;

    public Customer(String username, String password, String email, String role, double balance) {
        super(username, password, email, role);
        this.balance = balance;
    }

    public static User LoginUser(String email, String password) throws LoginException {
        for (User user : getUserListArray()) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                if (user.getPassword().equals(password)) {
                    if (getShoppingCart() == null) {
                        ShoppingCart cart = new ShoppingCart();
                    }
                    return user;
                } else {
                    throw new LoginException("Wrong Password");
                }
            }
        }
        throw new LoginException("No Matching Email");
    }

    public void payForShoppingCart() {
        if (this.canBalanceCoverPayment()) {
            double payment = getShoppingCart().getTotalPrice();
            this.balance -= payment;
            User.getUserMapper().updateBalance(this);
        } else {
            // TODO: Throw error
        }
    }

    public boolean canBalanceCoverPayment() {
        if (this.shoppingCart.getTotalPrice() > this.balance) {
            return false;
        }
        return true;
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

}
