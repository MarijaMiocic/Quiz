package com.marijamiocic.controller;

import com.marijamiocic.model.Answer;
import com.marijamiocic.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database operations related to the Question entity.
 */
public class QuestionRepository {

    private static final String DB_URL = "jdbc:sqlite:quiz_database";

    /**
     * Returns the next order number for a question within a given category.
     * It retrieves the maximum existing order number from the database for the specified category
     * and increments it by one.
     *
     * @param categoryId ID of the game category
     * @return the next available order number
     */
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

    /**
     * Saves a new question and its associated answers to the database.
     * The method performs the operation in a transaction to ensure atomicity.
     * If the question or its category is not valid, or if the insertion fails,
     * the transaction is rolled back.
     *
     * @param question the question to be saved
     * @param answers a list of possible answers for the question
     * @return true if the question and answers were saved successfully, false otherwise
     */
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

    public static List<Question> getQuestionsWithAnswersForCategory(String categoryName) {
        String sql = """
        SELECT q.id AS qid, q.questionText, a.id AS aid, a.answerText, a.isCorrect
        FROM Questions q
        JOIN GameCategories gc ON q.categoryId = gc.id
        JOIN Answers a ON q.id = a.questionId
        WHERE gc.name = ? AND q.isActive = 1 AND a.isActive = 1
        ORDER BY q.orderNumber, a.id
    """;

        Map<Integer, Question> questionMap = new LinkedHashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int qid = rs.getInt("qid");

                Question q = questionMap.get(qid);
                if (q == null) {
                    q = new Question();
                    q.setId(qid);
                    q.setQuestionText(rs.getString("questionText"));
                    q.setAnswerCategory(null); // if needed
                    q.setActive(true);
                    questionMap.put(qid, q);
                }

                Answer a = new Answer();
                a.setId(rs.getInt("aid"));
                a.setAnswerText(rs.getString("answerText"));
                a.setCorrect(rs.getBoolean("isCorrect"));

                if (q.getAnswers() == null) {
                    q.setAnswers(new ArrayList<>());
                }
                q.getAnswers().add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(questionMap.values());
    }

}
