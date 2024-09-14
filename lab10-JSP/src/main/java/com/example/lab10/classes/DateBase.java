package com.example.lab10.classes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DateBase {

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
            return statement.executeQuery(sqlQuery);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int ExecuteUpdate(String sqlQuery) {
        try {
            return statement.executeUpdate(sqlQuery);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public ArrayList<University> getClasses() {
        ArrayList<University> unicTable = new ArrayList<>();
        ResultSet rs = ExecuteQuery("select * from universities");
        try {
            while (rs.next()) {
                University currentUniversity = new University();
                currentUniversity.setUniversityId(rs.getInt("id"));
                currentUniversity.setUniversityName(rs.getString("name"));
                currentUniversity.setUniversityRating(rs.getInt("rating"));
                currentUniversity.setUniversityAddress(rs.getString("address"));
                unicTable.add(currentUniversity);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return unicTable;
    }

    public void addUser(String login, String password, String status) throws SQLException {
        String query = "insert into status (name, password, status) values (?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setString(1, login);
        statement.setBytes(2, getHash(password));
        statement.setString(3, status);
        statement.executeUpdate();
        statement.close();
    }

    public ResultSet checkUser(String login, String password) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select * from status where name = ? and password = ?");
        statement.setString(1, login);
        statement.setBytes(2, getHash(password));
        return statement.executeQuery();
    }

    public ResultSet checkUsersCount(String login, String password) throws SQLException {
        PreparedStatement statement = con.prepareStatement(
                "select count(*) as 'count' from status where name = ? and password = ?");
        statement.setString(1, login);
        statement.setBytes(2, getHash(password));
        return statement.executeQuery();
    }
    public void closeConnection()
    {
        try {
            con.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] getHash(String passStr) {
        MessageDigest digest = null;
        byte[] hash = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            hash = digest.digest(passStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hash;
    }


}
