package com.marijamiocic.model;

import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a quiz question.
 */
@Entity
@Table(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private GameCategory category;

    @Column(nullable = false)
    private Integer orderNumber;

    @Column(nullable = false)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "answerCategoryId", nullable = false)
    private AnswerCategory answerCategory;

    @Column(nullable = false)
    private boolean isActive;

    public Question() {}

    public Question(GameCategory category, Integer orderNumber, String questionText,
                    AnswerCategory answerCategory, boolean isActive) {
        this.category = category;
        this.orderNumber = orderNumber;
        this.questionText = questionText;
        this.answerCategory = answerCategory;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameCategory getCategory() {
        return category;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public AnswerCategory getAnswerCategory() {
        return answerCategory;
    }

    public void setAnswerCategory(AnswerCategory answerCategory) {
        this.answerCategory = answerCategory;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", category=" + category +
                ", orderNumber=" + orderNumber +
                ", questionText='" + questionText + '\'' +
                ", answerCategory=" + answerCategory +
                ", isActive=" + isActive +
                '}';
    }

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
