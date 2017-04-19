package jdbc.dao;


import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {


    public UserDAO getInstance() {
        Connection connection = DBHelper.getConnection();
        SessionFactory factory = DBHelper.getSessionFactory();
        String nameDao = getInstanceName();
        if (nameDao.equals("hibernate")) {
            return new UserDaoHib(factory);
        } else if(nameDao.equals("jdbc")) {
            return new UserDaoJDBC(connection);
        } else {
            throw new IllegalArgumentException("Can't find a value of property 'hibernateORjdbc'");
        }
    }

    private String getInstanceName() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DBHelper.class.getResourceAsStream("db.properties");
            prop.load(inputStream);
            return prop.getProperty("hibernateORjdbc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Cannot find property hibernateORjdbc");
    }

}
