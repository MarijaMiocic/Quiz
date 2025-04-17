package com.marijamiocic.controller;

import com.marijamiocic.model.User;

import java.sql.*;

public class UserRepository {

    private static final String DB_URL = "jdbc:sqlite:quiz_database";

    public static boolean saveUser(User user) {
        String checkSql = "SELECT id FROM Users WHERE email = ? OR username = ?";
        String insertSql = "INSERT INTO Users (email, username, password, isAdmin, isActive) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // Check if user exists
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, user.getEmail());
            checkStmt.setString(2, user.getUsername());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) return false;

            // Insert user
            PreparedStatement stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setBoolean(4, user.isAdmin());
            stmt.setBoolean(5, user.isActive());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
