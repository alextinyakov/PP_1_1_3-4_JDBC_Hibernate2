package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/users";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
