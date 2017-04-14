package jdbc.dao;


import jdbc.UsersDataSet;
import jdbc.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBC implements UserDAO {
    private Executor executor;

    public UserDaoJDBC(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet getUserById(long id) {
        try {
            return executor.execQuery("select * from users where id=" + id, result -> {
                result.next();
                return new UsersDataSet(id, result.getString(2), result.getString(3), result.getString(4));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UsersDataSet();
    }

    public List<UsersDataSet> getAllUsers () {
        try {
            return executor.execQuery("select * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }

    public void insertUser(UsersDataSet user) {
        StringBuilder query = new StringBuilder();
        query.
                append("insert into users (user_name,last_name,password) values ('").  //start query
                append(user.getName()).
                append("','").
                append(user.getLast_name()).
                append("','").
                append(user.getPassword()).
                append("')");
        try {
            executor.execUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(UsersDataSet user) {
        StringBuilder query = new StringBuilder();
        query.append("update users set ").
                append("user_name=").
                append("'").
                append(user.getName()).
                append("'").
                append(", ").
                append("last_name=").
                append("'").
                append(user.getLast_name()).
                append("'").
                append(" where id=").
                append(user.getId());

        try {
            executor.execUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long id) {
        try {
            executor.execUpdate("delete from users where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
