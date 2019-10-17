package presentation;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String username = request.getParameter ("username");
        String password = request.getParameter("login");
        //User user = LogicFacade.login(username, password);
        HttpSession session = request.getSession();
       // session.setAttribute("user", user);
       // session.setAttribute("role", user.getRole());
       // return user.getRole() + "page";
       return password;
    }
}