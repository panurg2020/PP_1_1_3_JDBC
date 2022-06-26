package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Jklgfyr1";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
            throw new RuntimeException();
        }
    }
}
