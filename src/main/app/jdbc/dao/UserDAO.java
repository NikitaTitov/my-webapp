package jdbc.dao;


import jdbc.UsersDataSet;

import java.util.List;

public interface UserDAO {

    UsersDataSet getUserById(long id);

    UsersDataSet getUser(String userName, String password);

    List<UsersDataSet> getAllUsers ();

    void insertUser(UsersDataSet user);

    void updateUserData(UsersDataSet user);

    void deleteUser(long id);
}
