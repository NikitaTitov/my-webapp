package jdbc;


import jdbc.dao.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    private final Connection connection;

    public DBService(Connection connection) {
        this.connection = connection;
    }

    public UsersDataSet getUser(long id) {
        try {
            return (new DAO(connection).getUserById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new UsersDataSet();
    }

    public List<UsersDataSet> getAllUsers() {
        List<UsersDataSet> users = new ArrayList<>();
        try{
            DAO dao = new DAO(connection);
            return dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(UsersDataSet user) {
        try {
            connection.setAutoCommit(false);
            DAO dao = new DAO(connection);
            dao.createTestTable();
            dao.insertUser(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void updateUser(UsersDataSet user) {
        try {
            connection.setAutoCommit(false);
            DAO dao = new DAO(connection);
            dao.updateUserData(user);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void deleteUser(long id) {
        try {
            connection.setAutoCommit(false);
            DAO dao = new DAO(connection);
            dao.deleteUser(id);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void createTestTable(){
        DAO dao = new DAO(connection);
        try {
            dao.createTestTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
