package com.gmail.pashasimonpure.repository.impl;

import com.gmail.pashasimonpure.repository.UserRepository;
import com.gmail.pashasimonpure.repository.model.User;
import com.gmail.pashasimonpure.repository.model.UserInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {

        String sql = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("UserRepository: delete user failed, no rows affected.");
            }

        }
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {

        String sql = "INSERT INTO user(username, password, is_active, age) VALUES (?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getActive());
            statement.setInt(4, user.getAge());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("UserRepository: add user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("UserRepository: add user failed, no ID obtained.");
                }
            }

            return user;
        }

    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {

        String sql = "SELECT u.id, username, password, age, is_active, ui.telephone, ui.address " +
                "FROM user u LEFT JOIN user_information ui ON u.id = ui.user_id";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            List<User> users = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {

                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setActive(rs.getBoolean("is_active"));
                    user.setAge(rs.getInt("age"));

                    UserInformation userInfo = new UserInformation();
                    userInfo.setAddress(rs.getString("address"));
                    userInfo.setTelephone(rs.getString("telephone"));

                    user.setUseInfo(userInfo);

                    users.add(user);

                }

                return users;
            }
        }

    }

    @Override
    public void dropTable(Connection connection) throws SQLException {

        String sql = "DROP TABLE IF EXISTS user";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }

    }

    @Override
    public void createTable(Connection connection) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id BIGINT(11) AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(40) NOT NULL," +
                "password VARCHAR(40) NOT NULL, " +
                "is_active TINYINT(1) DEFAULT 0, " +
                "age INT(11))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }

    }

}