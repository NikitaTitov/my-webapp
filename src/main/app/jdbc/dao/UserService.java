package jdbc.dao;


import jdbc.UsersDataSet;

import java.util.List;

public interface UserService {

    UsersDataSet getUserById(long id);

    List<UsersDataSet> getAllUsers ();

    void insertUser(UsersDataSet user);

    void updateUserData(UsersDataSet user);

    void deleteUser(long id);
}
