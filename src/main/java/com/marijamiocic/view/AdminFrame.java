package com.marijamiocic.view;

import com.marijamiocic.controller.AnswerCategoryRepository;
import com.marijamiocic.controller.GameCategoryRepository;
import com.marijamiocic.controller.QuestionRepository;
import com.marijamiocic.model.Answer;
import com.marijamiocic.model.AnswerCategory;
import com.marijamiocic.model.GameCategory;
import com.marijamiocic.model.Question;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI frame for administrative users.
 * Displays the admin interface of the application.
 */
public class AdminFrame extends JFrame {

    private JTextField questionField;
    private JTextField[] answerFields;
    private JCheckBox[] correctCheckBoxes;
    private JButton saveButton;

    /**
     * Constructor for the admin frame.
     */
    public AdminFrame() {
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initComps();
        layoutComps();
        activateComps();

        setVisible(true);
    }

    private void initComps() {
        questionField = new JTextField(50);
        answerFields = new JTextField[4];
        correctCheckBoxes = new JCheckBox[4];

        for (int i = 0; i < 4; i++) {
            answerFields[i] = new JTextField(30);
            correctCheckBoxes[i] = new JCheckBox("Correct");
        }

        saveButton = new JButton("Save Question");
    }

    private void layoutComps() {
        setLayout(new MigLayout("wrap 2", "[right][grow]"));

        add(new JLabel("Enter question:"));
        add(questionField, "span, growx");

        for (int i = 0; i < 4; i++) {
            add(new JLabel("Answer " + (i + 1) + ":"));
            JPanel answerPanel = new JPanel();
            answerPanel.add(answerFields[i]);
            answerPanel.add(correctCheckBoxes[i]);
            add(answerPanel, "growx");
        }

        add(new JLabel(""));
        add(saveButton, "gaptop 20");
    }

    private void activateComps() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String questionText = questionField.getText().trim();

                if (questionText.isEmpty()) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Question cannot be empty.");
                    return;
                }

                List<Answer> answers = new ArrayList<>();
                int correctCount = 0;

                for (int i = 0; i < 4; i++) {
                    String answerText = answerFields[i].getText().trim();
                    boolean isCorrect = correctCheckBoxes[i].isSelected();

                    if (answerText.isEmpty()) {
                        JOptionPane.showMessageDialog(AdminFrame.this, "Answer " + (i + 1) + " cannot be empty.");
                        return;
                    }

                    if (isCorrect) correctCount++;

                    Answer a = new Answer();
                    a.setAnswerText(answerText);
                    a.setCorrect(isCorrect);
                    a.setActive(true);
                    answers.add(a);
                }

                if (correctCount == 0) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "At least one answer must be marked as correct.");
                    return;
                }

                // Kategorija (pretpostavimo id=1) i tip odgovora (pretpostavimo id=1)
                GameCategory category = GameCategoryRepository.findById(1); // trebaš imati metodu u repozitoriju
                if (category == null || category.getId() == null) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Category not found or has no ID!");
                    return;
                }
                AnswerCategory answerCategory = AnswerCategoryRepository.findById(1);

                if (category == null || answerCategory == null) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Category or answer category not found in DB.");
                    return;
                }

                int questionOrder = QuestionRepository.getNextOrderNumberForCategory(category.getId());

                Question question = new Question();
                question.setCategory(category);
                question.setQuestionText(questionText);
                question.setAnswerCategory(answerCategory);
                question.setOrderNumber(questionOrder);
                question.setActive(true);

                boolean saved = QuestionRepository.saveQuestionWithAnswers(question, answers);

                if (saved) {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Question saved!");
                    questionField.setText("");
                    for (int i = 0; i < 4; i++) {
                        answerFields[i].setText("");
                        correctCheckBoxes[i].setSelected(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminFrame.this, "Failed to save question.");
                }
            }
        });
    }
}
