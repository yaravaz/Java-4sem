package com.example.lab10;

import java.io.*;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.Enumeration;

import com.example.lab10.classes.DateBase;
import com.example.lab10.classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.xml.transform.Result;

@WebServlet(name = "firstServlet", value = "/FirstServlet")
public class HelloServlet extends HttpServlet {
    private static final DateBase db = new DateBase();
    @Override
    public void init() throws ServletException {
        db.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        db.ExecuteUpdate("delete from universities where id = " + id);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("current_user");
        request.setAttribute("name", user.getLogin());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String rating = request.getParameter("rating");
        String address = request.getParameter("address");
        int rs = db.ExecuteUpdate("insert into universities (name, rating, address) values ('"
                + name + "', " + rating + ", '" + address +"')");
        if(rs == 0){
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("current_user");
        request.setAttribute("name", user.getLogin());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}