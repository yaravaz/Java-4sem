package com.example.lab13.command.authorithation;

import com.example.lab13.command.*;
import com.example.lab13.exception.*;
import com.example.lab13.util.pages.Page;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

public class SingOutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SingOutCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("NAME");
        LOGGER.info("user was above: name:" + username);
        session.removeAttribute("NAME");
        return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
    }
}
