package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import presentation.Command;

public class FrontpageCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String NextJspPage = "index";
        
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        
        return NextJspPage;
    }
}