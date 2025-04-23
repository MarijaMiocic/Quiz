package com.marijamiocic.controller;

import com.marijamiocic.model.AnswerCategory;

import java.sql.*;

/**
 * Provides database access methods for the AnswerCategory entity.
 * Supports retrieval of active answer categories by their ID.
 */
public class AnswerCategoryRepository {

    private static final String DB_URL = "jdbc:sqlite:quiz_database";

    /**
     * Retrieves an active answer category from the database using its ID.
     *
     * @param id the ID of the answer category
     * @return an AnswerCategory object if found and active, otherwise null
     */
    public static AnswerCategory findById(int id) {
        String sql = "SELECT * FROM AnswerCategories WHERE id = ? AND isActive = 1";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int code = rs.getInt("code");
                String name = rs.getString("name");
                boolean isActive = rs.getBoolean("isActive");
                return new AnswerCategory(code, name, isActive);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
