
import by.poit.lab6.DAO;

import java.sql.*;

public class Main {
    public static void main(String[] args){
        try {
            DAO dataBase = new DAO();
            dataBase.getConnection();

            ResultSet rs = dataBase.ExecuteQuery("""
                    SELECT CITIES.Name, CITIZENTYPES.Name, CITIZENTYPES.Language, CITIZENCOUNT.Amount
                    FROM CITIES\s
                       	JOIN CITIZENCOUNT ON CITIES.IDcity = CITIZENCOUNT.IDcity
                      	JOIN CITIZENTYPES ON CITIZENTYPES.IDtype = CITIZENCOUNT.IDtype
                           	WHERE CITIZENTYPES.Language LIKE 'russian'""");

            int columns = 4;
            while(rs.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs.getString(i) + "\t|\t");
                }
                System.out.println();
            }
            System.out.println("\n------------------------------------------------");
            rs = dataBase.ExecuteQuery("""
                    SELECT CITIES.Name, CITIES.FoundationYear, CITIES.Area, CITIZENCOUNT.Amount
                    FROM CITIES\s
                      	JOIN CITIZENCOUNT ON CITIES.IDcity = CITIZENCOUNT.IDcity
                       	JOIN CITIZENTYPES ON CITIZENTYPES.IDtype = CITIZENCOUNT.IDtype
                          	WHERE CITIZENTYPES.Name LIKE 'belarusian'""");
            while(rs.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs.getString(i) + "\t|\t");
                }
                System.out.println();
            }
            System.out.println("\n------------------------------------------------");
            rs = dataBase.ExecuteQuery("""
                    SELECT CITIES.Name, CITIZENTYPES.Name, CITIZENTYPES.Language, CITIZENCOUNT.Amount
                    FROM CITIES\s
                    	JOIN CITIZENCOUNT ON CITIES.IDcity = CITIZENCOUNT.IDcity
                    	JOIN CITIZENTYPES ON CITIZENTYPES.IDtype = CITIZENCOUNT.IDtype
                    WHERE CITIES.FoundationYear = (SELECT MIN(CITIES.FoundationYear) FROM CITIES)
                    ORDER BY CITIZENCOUNT.Amount DESC LIMIT 1""");
            while(rs.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs.getString(i) + "\t|\t");
                }
                System.out.println();
            }

            System.out.println("\n------------------Prepared statement-------------------");
            rs = dataBase.ExecutePrepareStatement("russian");
            while(rs.next()){
                for (int i = 1; i <= columns; i++){
                    System.out.print(rs.getString(i) + "\t|\t");
                }
                System.out.println();
            }
            System.out.println("\n------------------Transaction---------------------");
            dataBase.ExecuteTransaction();
            dataBase.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
