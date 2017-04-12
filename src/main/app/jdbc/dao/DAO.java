package jdbc.dao;


import jdbc.UsersDataSet;
import jdbc.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DAO {
    private Executor executor;

    public DAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet getUserById(long id) throws SQLException{
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(id, result.getString(2), result.getString(3), result.getString(4));
        });
    }

    public List<UsersDataSet> getAllUsers () throws SQLException {
        return executor.execQuery("select * from users");
    }

    public void insertUser(UsersDataSet user) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.
                append("insert into users (user_name,last_name,password) values ('").  //start query
                append(user.getName()).
                append("','").
                append(user.getLast_name()).
                append("','").
                append(user.getPassword()).
                append("')");
        executor.execUpdate(query.toString());
    }

    public void updateUserData(UsersDataSet user) throws SQLException{
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

        executor.execUpdate(query.toString());
    }

    public void deleteUser(long id) throws SQLException {
        executor.execUpdate("delete from users where id=" + id);
    }

    public void createTestTable() throws SQLException {
        executor.
                execUpdate(
                        "create table if not exists users (id bigint auto_increment, user_name varchar(20), last_name varchar(20), password int, primary key (id))"
                );
    }

    public void dropTestTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
