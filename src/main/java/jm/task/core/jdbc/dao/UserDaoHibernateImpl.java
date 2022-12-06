package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static Transaction tx = null;
    public static SessionFactory se;
    private static Session session;

    //public static SessionFactory se = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
        se = Util.getSessionFactory();
        session = se.openSession();
    }


    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS User (ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(200), LASTNAME VARCHAR(200), AGE TINYINT)";

        try {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }


    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS User";
        try {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }

    @Override
    public void removeUserById(long id) {

        try {
            tx = session.beginTransaction();
            session.get(User.class, id);
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {

        try {
            tx = session.beginTransaction();
            return session.createQuery("FROM User", User.class).list();

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE User";
        try {
            tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

    }
}
