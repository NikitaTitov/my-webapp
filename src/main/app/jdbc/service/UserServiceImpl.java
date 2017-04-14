package jdbc.service;


import jdbc.UsersDataSet;
import jdbc.dao.UserDAO;
import jdbc.dao.UserDaoFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl singelton;
    private UserDAO userDAO;

    private UserServiceImpl(){
        UserDaoFactory factory = new UserDaoFactory();
        userDAO = factory.getInstance();
    }

    public static UserService getInstance() {
        return singelton = new UserServiceImpl();
    }

    @Override
    public UsersDataSet getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UsersDataSet> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void insertUser(UsersDataSet user) {
        userDAO.insertUser(user);
    }

    @Override
    public void updateUserData(UsersDataSet user) {
        userDAO.updateUserData(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteUser(id);
    }
}
