package com.quizapp.dao;

import com.quizapp.model.User;
import com.quizapp.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {

    public User getUserByUsername(String username) throws SQLException {
        String query = "SELECT id, username, password, role FROM users WHERE username = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }

    public boolean login(String username, String password) throws SQLException {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Welcome, " + user.getUsername() + "!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    public boolean register(String username, String password) throws SQLException {
        if (getUserByUsername(username) != null) {
            System.out.println("Username already exists.");
            return false;
        }

        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, "user");
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
