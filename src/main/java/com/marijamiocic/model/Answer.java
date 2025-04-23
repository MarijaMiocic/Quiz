package com.marijamiocic.model;

import jakarta.persistence.*;

/**
 * Represents an answer to a question.
 */
@Entity
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private boolean isCorrect;

    @Column(nullable = false)
    private boolean isActive;

    public Answer() {}

    public Answer(Question question, String answerText, boolean isCorrect, boolean isActive) {
        this.question = question;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", question=" + question +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", isActive=" + isActive +
                '}';
    }
}
