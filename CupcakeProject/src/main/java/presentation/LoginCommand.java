package presentation;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.User;

public class LoginCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        User.createUsersFromDB();
        String nextJspPage = "shoppage";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User user = User.LoginUser(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } catch (LoginException exception) {
            request.setAttribute("LoginError", exception);
            nextJspPage = "login";
        }

        return nextJspPage;
    }
}
// User user = LogicFacade.login(username, password);
// session.setAttribute("user", user);
// session.setAttribute("role", user.getRole());
       // return user.getRole() + "page";
