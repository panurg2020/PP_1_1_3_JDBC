package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connection = Util.getInstance().getConnection();
    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement(SQL_CREATE)) {
            pstm.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement("DROP TABLE IF EXISTS users")) {
            pstm.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)")) {
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users")) {
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return users;
    }


    public void cleanUsersTable() {
        try (PreparedStatement pstm = connection.prepareStatement("TRUNCATE TABLE users")) {
            pstm.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
