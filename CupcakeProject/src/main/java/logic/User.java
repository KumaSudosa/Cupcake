package logic;

import java.util.ArrayList;
import java.util.HashMap;
import javax.security.auth.login.LoginException;
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

    public User(String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
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
        
        if(noUsername || noPassword || noEmail){
            throw new IllegalArgumentException("remember to fill out all fields");
        }
        
        
        for (HashMap<String, String> map : userMapper.getUserList()) {
            String dbUsername = map.get("username");
            String dbEmail = map.get("email");
            if (username.toLowerCase().equals(dbUsername.toLowerCase())) {
                throw new IllegalArgumentException("username in use, try another name.");
            }
            if (!password2.toLowerCase().equals(password.toLowerCase())) {
                throw new IllegalArgumentException("passwords do not match.");
            }
            if (email.toLowerCase().equals(dbEmail.toLowerCase())) {
                throw new IllegalArgumentException("email not correct.");
            }
        }
        User newUser = new User(username, password, email, 0.0);
        userMapper.insertUser(newUser);
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