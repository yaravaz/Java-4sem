package by.poit.lab6;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.apache.log4j.xml.DOMConfigurator;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DAO implements IConnection, IQuery{
    static {
        new DOMConfigurator().doConfigure("log/log4j.xml", LogManager.getLoggerRepository());
    }

    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private static final Logger logger = Logger.getLogger(Main.class);
    public DAO(){}

    public Boolean getConnection() {
        try {
            logger.info("Started main");
            logger.debug("Connection...");
            String url = "jdbc:mysql://localhost:3306/forjava";
            String user = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            logger.info("Connected!");
            return true;
        }
        catch(Exception ex) {
            logger.error("error with connection!");
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
            logger.error("error with query");
            return null;
        }
    }
    public ResultSet ExecutePrepareStatement(String language) {
        try {
            String sqlQuery = """
                    SELECT CITIES.Name, CITIZENTYPES.Name, CITIZENTYPES.Language, CITIZENCOUNT.Amount
                    FROM CITIES\s
                    	JOIN CITIZENCOUNT ON CITIES.IDcity = CITIZENCOUNT.IDcity
                    	JOIN CITIZENTYPES ON CITIZENTYPES.IDtype = CITIZENCOUNT.IDtype
                    	WHERE CITIZENTYPES.Language LIKE ?;""";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, language);
            return preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("error with query");
            return null;
        }
    }

    public void ExecuteTransaction() {
        try {
            try {
                connection.setAutoCommit(false);

                statement.executeUpdate("UPDATE CITIES SET Area = 348 WHERE Name = 'Minsk'");
                connection.commit();

                ResultSet rs = statement.executeQuery("SELECT Name, Area FROM CITIES");
                int columns = 2;
                while(rs.next()) {
                    for(int i = 1; i <= columns; i++){
                        System.out.print(rs.getString(i) + "\t|\t");
                    }
                    System.out.println();
                }

                statement.executeUpdate("UPDATE CITIES SET Area = 111 WHERE Name = 'Minsk'");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                boolean error = true;
                if(error) {throw new SQLException("Error transaction");}


                connection.commit();
                rs = statement.executeQuery("SELECT Name, Area FROM CITIES");
                while(rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        System.out.print(rs.getString(i) + "\t|\t");
                    }
                    System.out.println();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                logger.error("error with transaction!");
                connection.rollback();
                ResultSet rs = statement.executeQuery("SELECT Name, Area FROM CITIES");
                int columns = 2;
                while(rs.next()) {
                    for(int i = 1; i <= columns; i++){
                        System.out.print(rs.getString(i) + "\t|\t");
                    }
                    System.out.println();
                }
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
            logger.error("error with transaction!");
        }

    }
    public void closeConnection()
    {
        try {
            connection.close();
            logger.info("connection closed!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error("error with close");
        }
    }

}
