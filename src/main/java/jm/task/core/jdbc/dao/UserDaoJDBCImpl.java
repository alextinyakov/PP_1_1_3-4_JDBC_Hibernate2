package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = Util.getConnection();


    public void createUsersTable() {
        String createUsersTable =
                "CREATE TABLE IF NOT EXISTS  users.user (Id INT PRIMARY KEY AUTO_INCREMENT, name TEXT(100), lastname TEXT(100),age INT);";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createUsersTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropUsersTable = ("Drop table IF EXISTS user;");

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropUsersTable);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = ("INSERT INTO user" +
                "(`name`, `lastname`, `age`) " +
                "VALUES (?, ?, ?);");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("User с именем - " + name + " добавлен в базу данных.");
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM user WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastname, age);
                listOfUsers.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listOfUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM user;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
