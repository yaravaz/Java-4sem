package com.example.lab13.repository;

import com.example.lab13.model.University;
import com.example.lab13.repository.dbconstants.*;

import java.util.Map;

public class SQLHelper {
    public static final String ID = "id";
    private static final String INSERT_QUERY = "INSERT INTO ";
    private static final String VALUES = "VALUES";
    private static final String WHERE = "WHERE ";
    private static final String SELECT = "SELECT";
    public static final String USER_TABLE = "Status";
    public static final String PERSON_TABLE = "universities";
    public final static String SQL_GET_PERSONS = "select * from " + USER_TABLE;
    public final static String SQL_INSERT_PERSON = "INSERT INTO " + PERSON_TABLE + "(" + UniversityTableConstants.NAME +
            "," + UniversityTableConstants.RATING + "," + UniversityTableConstants.ADDRESS + ") VALUES (? , ?, ?)";
    public final static String SQL_GET_USER = "SELECT " + UserTableConstants.ID.getFieldName() + ", " +
            UserTableConstants.LOGIN.getFieldName() + ", " +
            UserTableConstants.PASSWORD.getFieldName() + " from " + USER_TABLE + " WHERE " +
            UserTableConstants.LOGIN.getFieldName() + " =? and " +
            UserTableConstants.PASSWORD.getFieldName() + " =?";
    public final static String SQL_CHECK_LOGIN = "SELECT " + UserTableConstants.LOGIN.getFieldName() + " FROM " +
            USER_TABLE + " WHERE " + UserTableConstants.LOGIN.getFieldName() + " = ?";
    public final static String SQL_INSERT_USER = "INSERT INTO " + USER_TABLE + "(" +
            UserTableConstants.LOGIN.getFieldName() + " ," +
            UserTableConstants.PASSWORD.getFieldName() + ") VALUES (? , ?)";

    public static String makeInsertQuery(Map<String, Object> fields, String table) {
        StringBuilder query = new StringBuilder(INSERT_QUERY + " " + table + " ");
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            String column = entry.getKey();
            if (column.equals(ID)) {
                continue;
            }
            columns.append(column).append(", ");
            values.append("?, ");
        }
        values.deleteCharAt(values.lastIndexOf(","));
        columns.deleteCharAt(columns.lastIndexOf(","));
        values.append(")");
        columns.append(")");
        return  " " + query + " " + columns + " " + VALUES + " " + values + ";";
    }
}
