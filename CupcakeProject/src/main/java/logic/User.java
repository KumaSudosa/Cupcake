package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import persistence.CupcakeMapper;
import persistence.DB;
import persistence.IUserMapper;

/**
 *
 * @author Gruppe 3
 */
public class User {

    private String username;
    private String password;
    private String email;
    private double balance;
    private static IUserMapper userMapper;
    private static ArrayList<User> userList = new ArrayList();

    public static void setupMapper(IUserMapper mapper) {
        User.userMapper = mapper;
    }

    /**
     * werwerwerwer
     *
     * @param username
     * @param password
     * @param email
     * @param balance
     */
    public User(String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
        userList.add(this);
    }

    /**
     * @author Cahit
     * @param username
     * @param password
     * @param email
     * @throws IllegalArgumentException
     */
    public static void createUser(String username, String password, String email) throws IllegalArgumentException {

        for (HashMap<String, String> map : userMapper.getUserList()) {
            String dbUsername = map.get("username");
            String dbEmail = map.get("email");
            if (username.toLowerCase().equals(dbUsername.toLowerCase())) {
                throw new IllegalArgumentException("username in use, try with another name.");
            } else if (email.toLowerCase().equals(dbEmail.toLowerCase())) {
                throw new IllegalArgumentException("email not correct.");
            }
        }
        //consider adding starting funds
        User user = new User(username, password, email, 0);
        //need to add method to upload the new user to DB
    }

    public static void createUsersFromDB() {
        userList.clear();
        for (HashMap<String, String> map : userMapper.getUserList()) {
            String username = map.get("username");
            String password = map.get("login");
            String email = map.get("email");
            Double balance = Double.parseDouble(map.get("balance"));
            User user = new User(username, password, email, balance);
        }
    }

    /**
     * @author Michael N. Korsgaard
     * @param username
     * @param password
     * @return
     * @throws LoginException
     */
    public static User LoginUser(String username, String password) throws LoginException {
        for (User user : userList) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    throw new LoginException("Wrong Password");
                }
            }
        }
        throw new LoginException("No Matching Username");
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
