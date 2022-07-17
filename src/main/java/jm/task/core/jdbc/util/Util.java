package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;
    private static Util instance = null;

    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Jklgfyr1";
    public static final String URL = "jdbc:mysql://localhost:3306/users";


    private Util() {
        try {
            if (null == connection || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER_NAME,PASSWORD);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static Util getInstance() {
        if (null == instance) {
            instance = new Util();
        }
        return instance;
    }

    public static Connection getConnection() {

        return connection;
    }
}