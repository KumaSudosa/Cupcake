package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Michael N. Korsgaard
 */
public class GoToJspCommand extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String nextJsp = request.getParameter("goToJsp");
        return nextJsp;
    }
}