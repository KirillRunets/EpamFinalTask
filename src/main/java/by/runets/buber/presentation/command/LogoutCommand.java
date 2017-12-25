package presentation.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
