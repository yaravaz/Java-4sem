package com.example.lab13.filter;

import com.example.lab13.util.pages.Page;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = "/controller")
public class LoginRequiredFilter implements Filter {
    private static final String COMMAND = "command";
    private static final String WELCOME = "welcome";
    private static final String ERROR_MESSAGE = "error_message";
    private static final String ERROR_TEXT = "Нет авторизации для выполнения данной команды";
    private static final Logger LOGGER = Logger.getLogger(LoginRequiredFilter.class.getName());
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String command = request.getParameter(COMMAND);
        LOGGER.info("Filter is working " + COMMAND + "= " + command);
        if (command == null) {
            request.setAttribute(ERROR_MESSAGE, ERROR_TEXT);
            request.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(req, resp);
        }
        if (!command.equals(WELCOME)) {
            chain.doFilter(req, resp);
        } else {
            if (request.getSession().getAttribute("NAME") != null) {
                chain.doFilter(req, resp);
            } else {
                request.setAttribute(ERROR_MESSAGE, ERROR_TEXT);
                request.getRequestDispatcher(Page.ERROR_PAGE.getPage()).forward(req, resp);
            }
        }
    }
}
