package com.marijamiocic.controller;

import com.marijamiocic.model.Answer;
import com.marijamiocic.model.Question;

import java.sql.*;
import java.util.List;

public class QuestionRepository {

    private static final String DB_URL = "jdbc:sqlite:quiz_database";

    public static int getNextOrderNumberForCategory(int categoryId) {
        String sql = "SELECT MAX(orderNumber) AS maxOrder FROM Questions WHERE categoryId = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("maxOrder") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1; // default order number
    }

    public static boolean saveQuestionWithAnswers(Question question, List<Answer> answers) {
        String insertQuestion = "INSERT INTO Questions (categoryId, orderNumber, questionText, answerCategoryId, isActive) VALUES (?, ?, ?, ?, ?)";
        String insertAnswer = "INSERT INTO Answers (questionId, answerText, isCorrect, isActive) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false); // Start transaction

            if (question.getCategory() == null || question.getCategory().getId() == null) {
                throw new IllegalArgumentException("Question category or its ID is null");
            }

            // Insert question
            PreparedStatement questionStmt = conn.prepareStatement(insertQuestion, Statement.RETURN_GENERATED_KEYS);
            questionStmt.setInt(1, question.getCategory().getId());
            questionStmt.setInt(2, question.getOrderNumber());
            questionStmt.setString(3, question.getQuestionText());
            questionStmt.setInt(4, question.getCategory().getId());
            questionStmt.setBoolean(5, question.isActive());
            questionStmt.executeUpdate();

            ResultSet keys = questionStmt.getGeneratedKeys();
            if (keys.next()) {
                int questionId = keys.getInt(1);

                for (Answer answer : answers) {
                    PreparedStatement answerStmt = conn.prepareStatement(insertAnswer);
                    answerStmt.setInt(1, questionId);
                    answerStmt.setString(2, answer.getAnswerText());
                    answerStmt.setBoolean(3, answer.isCorrect());
                    answerStmt.setBoolean(4, answer.isActive());
                    answerStmt.executeUpdate();
                }

                conn.commit();
                return true;
            } else {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
