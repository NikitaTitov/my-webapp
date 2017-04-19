package jdbc.dao;


import jdbc.UsersDataSet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHib implements UserDAO {
private final SessionFactory sessionFactory;

    public UserDaoHib(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UsersDataSet getUserById(long id) {
        Session session = sessionFactory.openSession();
        UsersDataSet user = (UsersDataSet) session.get(UsersDataSet.class, id);
        session.close();
        return user;
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
                append(password.hashCode()).
                append("'");
        Session session = sessionFactory.openSession();
        Query hql = session.createQuery(query.toString());
        UsersDataSet user = (UsersDataSet) hql.uniqueResult();
        return user;
    }

    @Override
    public List<UsersDataSet> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query hql = session.createQuery("from jdbc.UsersDataSet");
        List<UsersDataSet> result = hql.list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void insertUser(UsersDataSet user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUserData(UsersDataSet user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StringBuilder query = new StringBuilder();
        query.append("update jdbc.UsersDataSet set ").
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
        Query hql = session.createQuery(query.toString());
        hql.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String query = "delete from jdbc.UsersDataSet where id = "+id;
        Query hql = session.createQuery(query);
        hql.executeUpdate();
        transaction.commit();
        session.close();
    }
}
