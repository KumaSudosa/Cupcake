/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.commands;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import presentation.Command;

/**
 *
 * @author Michael N. Korsgaard
 */
public class GoToShoppageCommand extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        String jspPage = "shoppage";
        return jspPage;
    }
    
}
