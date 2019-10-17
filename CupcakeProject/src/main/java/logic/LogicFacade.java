package logic;

public class LogicFacade {

    public static User login(String email, String password) throws LoginException {
        return DBFacade.login(email, password);
    }

    public static User createUser(String email, String password) throws LoginException {
        User user = new User(email, password, "customer");
        DBFacade.createUser(user);
        return user;
    }
}