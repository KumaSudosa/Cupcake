package logic;

import java.util.ArrayList;
import java.util.HashMap;
import javax.security.auth.login.LoginException;
import persistence.mappers.UserMapperInterface;

/**
 *
 * @author Gruppe 3
 */
public class User {

    private String username;
    private String password;
    private String email;
    private double balance;
    private String role;
    private ShoppingCart shoppingCart;
    private static UserMapperInterface userMapper;
    private static ArrayList<User> userList = new ArrayList();

    public static void setupMapper(UserMapperInterface mapper) {
        User.userMapper = mapper;
    }

    public static User getUserFromUserList(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        throw new IllegalArgumentException("User not found in userList");
    }

    public User(String username, String password, String email, double balance, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.role = role;
        userList.add(this);
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    /**
     * @author Cahit
     * @param username
     * @param password
     * @param email
     * @throws IllegalArgumentException
     */
    public static void createUsersFromDB() {
        userList.clear();
        for (HashMap<String, String> map : userMapper.getUserList()) {
            String username = map.get("username");
            String password = map.get("login");
            String email = map.get("email");
            Double balance = Double.parseDouble(map.get("balance"));
            String role = map.get("role");
            User user = new User(username, password, email, balance, role);
        }
    }

    /**
     * @author Michael N. Korsgaard
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    public static User LoginUser(String email, String password) throws LoginException {
        for (User user : userList) {
            if (user.getEmail().toLowerCase().equals(email.toLowerCase())) {
                if (user.getPassword().equals(password)) {
                    if (user.shoppingCart == null) {
                        user.shoppingCart = new ShoppingCart();
                    }
                    return user;
                } else {
                    throw new LoginException("Wrong Password");
                }
            }
        }
        throw new LoginException("No Matching Email");
    }

    /**
     * @author Marcus & Cahit
     * @param username
     * @param password
     * @param password2
     * @param email
     * @throws IllegalArgumentException
     */
    public static void RegisterUser(String username, String password, String password2, String email) throws IllegalArgumentException {

        //check for unfilled forms in registration
        boolean noUsername = username.length() < 1;
        boolean noPassword = password.length() < 1;
        boolean noEmail = email.length() < 1;

        if (noUsername || noPassword || noEmail) {
            throw new IllegalArgumentException("remember to fill out all fields");
        }
        pwCheck(password);

        for (HashMap<String, String> map : userMapper.getUserList()) {
            String dbEmail = map.get("email");

            if (email.toLowerCase().equals(dbEmail.toLowerCase())) {
                throw new IllegalArgumentException("email is already in use.");
            }
        }
        if (!password2.equals(password)) {
            throw new IllegalArgumentException("passwords do not match.");
        }
        User newUser = new User(username, password, email, 0.0, "c");
        userMapper.insertUser(newUser);
    }

    public boolean canBalanceCoverPayment() {
        if (this.shoppingCart.getTotalPrice() > this.balance) {
            return false;
        }
        return true;
    }

    public static void pwCheck(String password) {
        boolean letter = false;
        boolean upperLetter = false;
        boolean lowerLetter = false;
        boolean number = false;

        //Skal være mellem 6 og 20 karaktere for at overhovedet at komme ind i if statement
        if (password.length() >= 6 && 20 >= password.length()) {

            for (int i = 0; i < password.length(); i++) {
                char s = password.charAt(i);

                if (Character.isLetter(s)) {
                    letter = true;
                    //Bliver true hvis den møder minimum et bogstav
                }
                if (Character.isUpperCase(s)) {
                    upperLetter = true;
                    //Bliver true hvis den minimum et UpperCase bogstav
                }
                if (Character.isLowerCase(s)) {
                    lowerLetter = true;
                    //Bliver true hvis den møder minimum et LowerCase bogstav
                }
                if (Character.isDigit(s)) {
                    number = true;
                    //Bliver true hvis den møder minimum et tal
                }
            }

            //Tjekker at alt er som det skal være ellers kaster den en fejl
            if (!lowerLetter || !upperLetter || !number || !letter) {
                throw new IllegalArgumentException("Your password needs to contain at least an "
                        + "uppercase letter, lowercase letter, a number and be between 6 and 20 characters. Please try again.");
            }
        } else {
            throw new IllegalArgumentException("Your password needs to contain at least a "
                    + "uppercase letter, lowercase letter, a number and be between 6 and 20 characters. Please try again.");
        }
    }

    public void payForShoppingCart() {
        if (this.canBalanceCoverPayment()) {
            double payment = shoppingCart.getTotalPrice();
            this.balance -= payment;
            userMapper.updateBalance(this);
        } else {
            // TODO: Throw error
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getRole() {
        return role;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}