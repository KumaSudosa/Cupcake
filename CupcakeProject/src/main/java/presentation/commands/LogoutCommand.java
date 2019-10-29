package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import presentation.Command;

public class LogoutCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        // Get parameters and set initial nextJspPage String
        String NextJspPage = "index";
        
        // Logic calls
        request.getSession().setAttribute("user", null);
        
        // Set Attributes and go to next Page
        return NextJspPage;
    }
}