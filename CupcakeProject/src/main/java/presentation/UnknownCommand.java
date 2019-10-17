package presentation;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnknownCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String msg = "Unknown command. Contact IT";
        throw new LoginException(msg);
    }
}