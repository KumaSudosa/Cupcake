package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.User;
import presentation.Command;

/**
 *
 * @author Michael N. Korsgaard
 */
public class GoToJspCommand extends Command {

    /**
     * @author Gruppe 3
     *
     * Checks whether user is customer or admin
     *
     *
     * @param request html request from FrontController
     * @param response html request response FrontController
     * @return String for next JSP by stringName.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        String nextJsp = request.getParameter("goToJsp");
        if (nextJsp.equals("userPage")) {
            if (User.isUserAdmin(user)) {
                nextJsp = "admin";
            } else if (User.isUserCustomer(user)) {
                nextJsp = "shoppage";
            } else {
                nextJsp = "index";
            }
        }
        return nextJsp;
    }
}
