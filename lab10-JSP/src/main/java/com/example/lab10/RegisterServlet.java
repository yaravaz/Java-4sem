package com.example.lab10;

import com.example.lab10.classes.DateBase;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final DateBase db = new DateBase();

    @Override
    public void init(){
        db.getConnection();

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String status;
        if(request.getParameter("status") == null){
            status = "user";
        }
        else{
            status = request.getParameter("status");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (login.length() != 0 && password.length() != 0) {
            ResultSet rs = db.ExecuteQuery("select count(*) as 'count' from status where name = '" + login + "';");
            try {
                if(rs != null){
                    rs.next();
                    if (rs.getInt("count") != 0) {
                        ShowMessage(out, "There is an account", "Register.jsp");
                    }
                    else {
                        db.addUser(login, password, status);
                        ShowMessage(out, "Success", "Login.jsp");
                        out.close();

                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            ShowMessage(out, "Fill fields", "Register.jsp");
            out.close();
        }
    }
    private void ShowMessage(PrintWriter out, String message, String location) {
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + message + "');");
        out.println("window.location.href = '" + location + "';");
        out.println("</script>");
        out.println("</body></html>");
    }
}
