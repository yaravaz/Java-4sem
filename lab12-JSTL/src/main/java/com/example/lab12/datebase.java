package com.example.lab12;

import java.sql.*;

public class datebase {
    private String url = "jdbc:mysql://localhost:3306/forJava";
    private String login = "root";
    private String password = "password";
    private Connection con;
    private Statement statement;

    public Boolean getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, login, password);
            statement = con.createStatement();
            return true;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public ResultSet ExecuteQuery(String sqlQuery) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
