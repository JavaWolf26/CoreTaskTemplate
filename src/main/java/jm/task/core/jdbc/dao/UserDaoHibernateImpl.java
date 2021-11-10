package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users.user_table" +
                            "(id bigint not null auto_increment primary key, " +
                            "name varchar(55) not null, lastName varchar(55) not null, age tinyint not null)")
                    .addEntity(User.class).executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Создание таблицы не удалось");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users.user_table")
                    .addEntity(User.class).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Удаление таблицы не удалось");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            tx.commit();
        } catch (Exception e) {
            System.out.println("Сохранение пользователя не удалось");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            tx.commit();
        } catch (Exception e) {
            System.out.println("Удаление пользователя не удалось");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            users = session.createQuery("FROM User", User.class).list();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Полная выборка пользователей не удалась");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users.user_table")
                    .addEntity(User.class).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Очищение таблицы не удалось");
            e.printStackTrace();
        }
    }
}
