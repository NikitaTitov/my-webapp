package jdbc.dao;


import org.hibernate.SessionFactory;
import java.sql.Connection;

public class UserDaoFactory {


    public UserDAO getInstance() {
        Connection connection = DBHelper.getConnection();
        SessionFactory factory = DBHelper.getSessionFactory();
        if (connection == null) {
            return new UserDaoHib(factory);
        } else {
            return new UserDaoJDBC(connection);
        }
    }

}
