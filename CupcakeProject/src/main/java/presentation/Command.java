package presentation;

import presentation.commands.ConfirmationCommand;
import presentation.commands.RegistrationCommand;
import presentation.commands.ProductsCommand;
import presentation.commands.EntryCommand;
import presentation.commands.ShoppageCommand;
import presentation.commands.LoginCommand;
import presentation.commands.UnknownCommand;
import java.util.HashMap;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("products", new ProductsCommand());
        commands.put("confirmation", new ConfirmationCommand());
        commands.put("shoppage", new ShoppageCommand());
        commands.put("entry", new EntryCommand());
    }

    public static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws LoginException;
}
