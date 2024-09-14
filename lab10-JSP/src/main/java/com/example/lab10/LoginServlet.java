package com.example.lab10;

import com.example.lab10.classes.DateBase;
import com.example.lab10.classes.User;
import org.apache.logging.log4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static Logger logger = LogManager.getLogger();

    private static final DateBase db = new DateBase();

    @Override
    public void init(){
        db.getConnection();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUserFound = false;
        Date currentDate = new Date();
        User user = new User();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        try {
            ResultSet rs = db.checkUsersCount(login, password);
            rs.next();
            if (rs.getInt("count") != 0) {
                logger.info("login success");
                ResultSet userSet = db.checkUser(login, password);
                userSet.next();
                user.setLogin(userSet.getString("name"));
                user.setStatus(userSet.getString("status"));
                isUserFound = true;
            }
            else {
                user = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HttpSession session = request.getSession();
        session.setAttribute("current_user", user);
        if (isUserFound) {
            logger.info("login success");
            request.setAttribute("name", user.getLogin());
            request.setAttribute("status", user.getStatus());
            request.setAttribute("lastVisit", currentDate);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            request.setAttribute("ErrorMessage", "incorrect password or login");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }

        out.println("</body></html>");
        out.close();
    }
}
