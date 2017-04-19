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
    @Override
    public UsersDataSet getUserById(long id) {
        try {
            return executor.execQuery("select * from users where id=" + id, result -> {
                result.next();
                return new UsersDataSet(
                        id,
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UsersDataSet();
    }

    @Override
    public UsersDataSet getUser(String userName, String password) {
        StringBuilder query = new StringBuilder();
        query.
                append("select * from users where user_name=").
                append("'").
                append(userName).
                append("'").
                append(" and ").
                append("password=").
                append("'").
                append(password).
                append("'");
        try {
            return executor.execQuery(query.toString(), result -> {
                result.next();
                return new UsersDataSet(
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UsersDataSet();
    }
    @Override
    public List<UsersDataSet> getAllUsers () {
        try {
            return executor.execQuery("select * from users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
    @Override
    public void insertUser(UsersDataSet user) {
        StringBuilder query = new StringBuilder();
        query.
                append("insert into users (user_name,last_name,password,user_right) values ('").
                append(user.getName()).
                append("','").
                append(user.getLastName()).
                append("','").
                append(user.getPassword()).
                append("','").
                append(user.getUserRight()).
                append("')");
        try {
            executor.execUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
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
                append(user.getLastName()).
                append("'").
                append(", ").
                append("user_right=").
                append("'").
                append(user.getUserRight()).
                append("'").
                append(" where id=").
                append(user.getId());

        try {
            executor.execUpdate(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteUser(long id) {
        try {
            executor.execUpdate("delete from users where id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
