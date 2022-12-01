package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Util.getConnection();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Александр", "Александров", (byte) 33);
        userService.saveUser("Петр", "Петров", (byte) 44);
        userService.saveUser("Дмитрий", "Дмитриев", (byte) 55);
        userService.saveUser("Владимир", "Владимиров", (byte) 66);

        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

        try {
            Util.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
