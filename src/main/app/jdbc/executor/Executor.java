package jdbc.executor;


import jdbc.UsersDataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(update);
        statement.close();
    }

    public <T> T execQuery(String query,
                           ResultHandler<T> handler)
            throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value;
        if(result != null) {
            value = handler.handle(result);
        }
        else {
            value = null;
        }
        result.close();
        stmt.close();

        return value;
    }



    public <T extends UsersDataSet> List<UsersDataSet> execQuery(String query)
            throws SQLException {
        List<UsersDataSet> users = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        while (result.next()){
            UsersDataSet user = new UsersDataSet(
                    result.getLong("id"),
                    result.getString("user_name"),
                    result.getString("last_name"),
                    result.getString("password"));
            users.add(user);
        }

        result.close();
        stmt.close();

        return users;
    }
}
