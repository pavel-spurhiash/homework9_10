package com.gmail.pashasimonpure.repository.impl;

import com.gmail.pashasimonpure.repository.UserInformationRepository;
import com.gmail.pashasimonpure.repository.model.UserInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInformationRepositoryImpl implements UserInformationRepository {

    private static UserInformationRepository instance;

    private UserInformationRepositoryImpl() {
    }

    public static UserInformationRepository getInstance() {
        if (instance == null) {
            instance = new UserInformationRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void delete(Connection connection, Long id) throws SQLException {

        String sql = "DELETE FROM user_information WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("UserInformationRepository: delete user info failed, no rows affected.");
            }

        }
    }

    @Override
    public UserInformation add(Connection connection, UserInformation userInfo) throws SQLException {

        String sql = "INSERT INTO user_information(user_id, address, telephone) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, userInfo.getId());
            statement.setString(2, userInfo.getAddress());
            statement.setString(3, userInfo.getTelephone());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("UserInformationRepository: add user information failed, no rows affected.");
            }

            return userInfo;
        }
    }

    @Override
    public void update(Connection connection, UserInformation userInfo) throws SQLException {

        String sql = "UPDATE user_information SET address = ?, telephone = ? WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, userInfo.getAddress());
            statement.setString(2, userInfo.getTelephone());
            statement.setLong(3, userInfo.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("UserInformationRepository: add user information failed, no rows affected.");
            }

        }
    }

    @Override
    public UserInformation get(Connection connection, Long id) throws SQLException {

        String sql = "SELECT user_id, address, telephone FROM user_information WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery()) {

                UserInformation userInfo = new UserInformation();
                if (rs.next()) {
                    userInfo.setId(rs.getLong("user_id"));
                    userInfo.setAddress(rs.getString("address"));
                    userInfo.setTelephone(rs.getString("telephone"));
                }
                return userInfo;
            }

        }

    }

    @Override
    public List<UserInformation> findAll(Connection connection) throws SQLException {

        String sql = "SELECT user_id, address, telephone FROM user_information";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            List<UserInformation> usersInfo = new ArrayList<>();

            try (ResultSet rs = statement.executeQuery()) {

                while (rs.next()) {

                    UserInformation userInfo = new UserInformation();
                    userInfo.setId(rs.getLong("user_id"));
                    userInfo.setAddress(rs.getString("address"));
                    userInfo.setTelephone(rs.getString("telephone"));

                    usersInfo.add(userInfo);

                }

                return usersInfo;
            }
        }

    }

    @Override
    public void dropTable(Connection connection) throws SQLException {

        String sql = "DROP TABLE IF EXISTS user_information";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }

    }

    @Override
    public void createTable(Connection connection) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS user_information (" +
                "user_id BIGINT(11) PRIMARY KEY NOT NULL, " +
                "address VARCHAR(100)," +
                "telephone VARCHAR(40), " +
                "FOREIGN KEY(user_id) REFERENCES user(id))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

}
