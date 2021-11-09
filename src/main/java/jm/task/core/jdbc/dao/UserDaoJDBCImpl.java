package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user.user_table ("
                + "ID BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, "
                + "name VARCHAR(55), "
                + "lastName VARCHAR(55), "
                + "age TINYINT(3) "
                + ")";
        try (Connection connection = new Util().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Создание таблицы не удалось");
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user.user_table";
        try (Connection connection = new Util().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Удаление таблицы не удалось");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user.user_table (name, lastName, age) VALUES(?, ?, ?)";
        try (Connection connection = new Util().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Сохранение пользователя не удалось");
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM user.user_table WHERE id=?";
        try (Connection connection = new Util().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Удаление пользователя не удалось");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user.user_table";
        try (Connection connection = new Util().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Полная выборка пользователей не удалась");
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user.user_table WHERE id>0";
        try (Connection connection = new Util().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Очищение таблицы не удалось");
        }
    }
}