package jdbc.dao;


import jdbc.UsersDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    public static Connection getConnection() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DBHelper.class.getResourceAsStream("db.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            if (driver != null) {
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                return DriverManager.getConnection(url, user, password);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        try {
            Properties prop = new Properties();
            InputStream inputStream = DBHelper.class.getResourceAsStream("db.properties");
            prop.load(inputStream);

            String driver = prop.getProperty("hibernate.connection.driver_class");
            if (driver != null) {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(UsersDataSet.class);

                configuration.setProperty("hibernate.dialect", prop.getProperty("hibernate.dialect"));
                configuration.setProperty("hibernate.connection.driver_class", prop.getProperty("hibernate.connection.driver_class"));
                configuration.setProperty("hibernate.connection.url", prop.getProperty("hibernate.connection.url"));
                configuration.setProperty("hibernate.connection.username", prop.getProperty("hibernate.connection.username"));
                configuration.setProperty("hibernate.connection.password", prop.getProperty("hibernate.connection.password"));
                configuration.setProperty("hibernate.show_sql", prop.getProperty("hibernate.show_sql"));
                configuration.setProperty("hibernate.hbm2ddl.auto", prop.getProperty("hibernate.hbm2ddl.auto"));

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                builder.applySettings(configuration.getProperties());
                ServiceRegistry service = builder.build();
                return configuration.buildSessionFactory(service);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
