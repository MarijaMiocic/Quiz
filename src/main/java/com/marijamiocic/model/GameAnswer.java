package com.marijamiocic.model;

import jakarta.persistence.*;

/**
 * Represents an answer selection by the user in a game.
 */
@Entity
@Table(name = "GameAnswers")
public class GameAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "answerId", nullable = false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "gameQuestionId", nullable = false)
    private GameQuestion gameQuestion;

    @Column(nullable = false)
    private boolean selectedAnswer;

    public GameAnswer() {}

    public GameAnswer(Answer answer, GameQuestion gameQuestion, boolean selectedAnswer) {
        this.answer = answer;
        this.gameQuestion = gameQuestion;
        this.selectedAnswer = selectedAnswer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public GameQuestion getGameQuestion() {
        return gameQuestion;
    }

    public void setGameQuestion(GameQuestion gameQuestion) {
        this.gameQuestion = gameQuestion;
    }

    public boolean isSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(boolean selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
