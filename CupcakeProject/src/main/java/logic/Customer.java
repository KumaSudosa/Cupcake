package logic;

/**
 * @author Andreas
 */
public class Customer extends User {

    private double balance;
    private ShoppingCart shoppingCart;

    public Customer(String username, String password, String email, String role, double balance) {
        super(username, password, email, role);
        this.balance = balance;
    }

    /**
     * method subtracts the shopping cart's total price from the user's balance and updates it.
     * @throws exception if it doesn't work, I.E the shopping cart price can't be taken from the user's balance
     */
    public void payForShoppingCart() {
        if (this.canBalanceCoverPayment()) {
            double payment = getShoppingCart().getTotalPrice();
            this.balance -= payment;
            User.getUserMapper().updateBalance(this);
        } else {
            throw new IllegalArgumentException("There is an error with the shopping cart");
        }
    }

    /**
     * @return method checks if the total purchase price in the shopping cart is higher than the user's balance.
     * if the purchase price is higher it returns "false", and if the purchase is not higher it returns "true"
     */
    public boolean canBalanceCoverPayment() {
        if (this.shoppingCart.getTotalPrice() > this.balance) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param email method takes an email and finds the customer that has the specific email
     * @return returns the customer that matches with the email
     * @throws exception if the email doesn't match a user (doesn't exist)
     */
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}