package com.marijamiocic.view;

import com.marijamiocic.controller.QuestionRepository;
import com.marijamiocic.model.Answer;
import com.marijamiocic.model.Question;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

/**
 * GUI frame for playing a quiz.
 * Displays questions with multiple-choice answers.
 */
public class PlayFrame extends JFrame {

    private JLabel questionLabel;
    private JRadioButton[] answerButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    /**
     * Constructs the play frame and loads questions from the database.
     */
    public PlayFrame() {
        setTitle("Quiz - General Knowledge");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new MigLayout("wrap 1", "[grow, center]", "[]20[]10[]10[]10[]30[]"));
        setResizable(false);

        initComps();
        layoutComps();
        activateComps();

        loadQuestions2();
        if (questions != null && !questions.isEmpty()) {
            showQuestion(currentQuestionIndex);
        }

        setVisible(true);
    }

    /**
     * Initializes GUI components.
     */
    private void initComps() {
        questionLabel = new JLabel();
        questionLabel.setFont(questionLabel.getFont().deriveFont(20f));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        answerButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton();
            buttonGroup.add(answerButtons[i]);
        }

        nextButton = new JButton("Next");
    }

    /**
     * Arranges components using MigLayout.
     */
    private void layoutComps() {
        add(questionLabel, "gapbottom 10");
        for (JRadioButton btn : answerButtons) {
            add(btn, "growx");
        }
        add(nextButton, "gaptop 20");
    }

    /**
     * Activates event listeners.
     */
    private void activateComps() {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion(currentQuestionIndex);
                } else {
                    showResult();
                }
            }
        });
    }

    /**
     * Loads questions from the database.
     */
    private void loadQuestions() {
        this.questions = QuestionRepository.getQuestionsWithAnswersForCategory("General Knowledge");
        if (questions == null || questions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No questions found for this category.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose(); // Close the frame if no questions
        }
    }

    private void loadQuestions2() {
        List<Question> allQuestions = QuestionRepository.getQuestionsWithAnswersForCategory("General Knowledge");

        if (allQuestions == null || allQuestions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No questions found for this category.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        Collections.shuffle(allQuestions);
        this.questions = allQuestions.subList(0, Math.min(4, allQuestions.size()));
    }

    /**
     * Displays a specific question and its answers.
     */
    private void showQuestion(int index) {
        Question question = questions.get(index);
        questionLabel.setText((index + 1) + ". " + question.getQuestionText());

        List<Answer> answers = question.getAnswers();
        for (int i = 0; i < answerButtons.length; i++) {
            if (i < answers.size()) {
                answerButtons[i].setText(answers.get(i).getAnswerText());
                answerButtons[i].setVisible(true);
            } else {
                answerButtons[i].setVisible(false);
            }
        }
        buttonGroup.clearSelection();
    }

    /**
     * Checks if the selected answer is correct.
     */
    private void checkAnswer() {
        List<Answer> answers = questions.get(currentQuestionIndex).getAnswers();

        for (int i = 0; i < answers.size(); i++) {
            if (answerButtons[i].isSelected() && answers.get(i).isCorrect()) {
                correctAnswers++;
                break;
            }
        }
    }

    /**
     * Shows the final quiz result to the user.
     */
    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "You got " + correctAnswers + " out of " + questions.size() + " correct!",
                "Quiz Result",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
