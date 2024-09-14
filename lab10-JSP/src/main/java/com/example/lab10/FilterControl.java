package com.example.lab10;

import com.example.lab10.classes.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "FilterControl", urlPatterns = "/index.jsp")
public class FilterControl implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session =request.getSession();
        User user = (User)session.getAttribute("current_user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/ErrorPage.jsp");
        }
        else{
            req.setAttribute("name", user.getLogin());
            chain.doFilter(request, response);
        }

    }
}
