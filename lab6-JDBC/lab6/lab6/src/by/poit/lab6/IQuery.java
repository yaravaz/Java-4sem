package by.poit.lab6;

import java.sql.ResultSet;

public interface IQuery {
    public ResultSet ExecuteQuery(String sqlQuery);
    public ResultSet ExecutePrepareStatement(String language);
    public void ExecuteTransaction();
}
