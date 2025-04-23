package com.marijamiocic.controller;

import com.marijamiocic.model.GameCategory;

import java.sql.*;

public class GameCategoryRepository {

    private static final String DB_URL = "jdbc:sqlite:quiz_database";

    public static GameCategory findById(int id) {
        String sql = "SELECT * FROM GameCategories WHERE id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:quiz_database");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                GameCategory category = new GameCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setActive(rs.getBoolean("isActive"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
