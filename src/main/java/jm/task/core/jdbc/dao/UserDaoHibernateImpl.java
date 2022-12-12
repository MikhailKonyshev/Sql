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
    public static SessionFactory se;

    public UserDaoHibernateImpl() {
        se = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {

        Transaction tx = null;

        String sql = "CREATE TABLE IF NOT EXISTS User (ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(200), LASTNAME VARCHAR(200), AGE TINYINT)";

        try {
            Session session = se.getCurrentSession();
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

        Transaction tx = null;

        String sql = "DROP TABLE IF EXISTS User";
        try {
            Session session = se.getCurrentSession();
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

        Transaction tx = null;

        try {
            Session session = se.getCurrentSession();
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

        Transaction tx = null;

        try {
            Session session = se.getCurrentSession();
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
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

        Transaction tx = null;

        List<User> users = null;
        try {
            Session session = se.getCurrentSession();
            tx = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            tx.commit();
            return users;

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {

        Transaction tx = null;

        String sql = "TRUNCATE User";
        try {
            Session session = se.getCurrentSession();
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
