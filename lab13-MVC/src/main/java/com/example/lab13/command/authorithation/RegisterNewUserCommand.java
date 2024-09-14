package com.example.lab13.command.authorithation;

import com.example.lab13.command.*;
import com.example.lab13.exception.*;
import com.example.lab13.model.User;
import com.example.lab13.service.UserService;
import com.example.lab13.util.HashPassword;
import com.example.lab13.util.pages.Page;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.util.Optional;

import static com.example.lab13.command.authorithation.constants.AuthConstants.*;
import static com.microsoft.sqlserver.jdbc.StringUtils.isEmpty;

public class RegisterNewUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegisterNewUserCommand.class.getName());
    private CommandResult forwardToRegisterWithError(HttpServletRequest request, String ERROR, String ERROR_MESSAGE) {
        request.setAttribute(ERROR, ERROR_MESSAGE);
        return new CommandResult(Page.REGISTER_PAGE.getPage(), false);
    }
    private CommandResult forwardToLogin(HttpServletRequest request) {
        return new CommandResult(Page.LOGIN_PAGE.getPage(), false);
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Optional<String> login = Optional.ofNullable(request.getParameter(NAME_FOR_REGISTER));
        Optional<String> password = Optional.ofNullable(request.getParameter(PASSWORD_FOR_REGISTER));
        if (isEmpty(login.get()) || isEmpty(password.get())) {
            LOGGER.info("invalid login or password format was received:" + login + " " + password);
            return forwardToRegisterWithError(request, REGISTER_ERROR, ERROR_MESSAGE_TEXT);
        }
        byte[] pass = HashPassword.getHash(password.get());
        User user = new User(login.get(), pass);
        UserService userService = new UserService();
        int userCount = userService.save(user);
        if (userCount != 0) {
            LOGGER.info("user was registered: login:" + login + " password:" + password);
            return forwardToLogin(request);
        } else {
            LOGGER.info("invalid login or password format was received:" + login + " " + password);
            return forwardToRegisterWithError(request, REGISTER_ERROR, REGISTER_ERROR_MESSAGE_IF_EXIST);
        }
    }
}