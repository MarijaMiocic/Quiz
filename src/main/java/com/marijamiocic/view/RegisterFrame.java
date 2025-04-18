package com.marijamiocic.view;

import com.marijamiocic.controller.UserRepository;
import com.marijamiocic.model.User;
import com.marijamiocic.util.HashUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Registration form for new users.
 */
public class RegisterFrame extends JFrame {

    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    /**
     * Constructor for the register frame.
     */
    public RegisterFrame() {
        super("Register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        initComps();
        layoutComps();
        activateComps();
    }

    /**
     * Initializes the components for the register frame.
     */
    private void initComps() {
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");
    }

    /**
     * Configures the layout of the components in the register frame using MigLayout.
     * Adds labels and text fields to the layout.
     */
    private void layoutComps() {
        setLayout(new MigLayout("insets 70 50 50 50, center", "[right][grow]", "[][][][]"));
        add(new JLabel("Email:"));
        add(emailField, "wrap");
        add(new JLabel("Username:"));
        add(usernameField, "wrap");
        add(new JLabel("Password:"));
        add(passwordField, "wrap");
        add(registerButton, "gaptop 10, span 2, center");
    }

    /**
     * Adds logic to the register button for saving the new user.
     */
    private void activateComps() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String hashedPassword = HashUtil.hashPassword(password);

                User user = new User(email, username, hashedPassword, false, true);
                boolean success = UserRepository.saveUser(user);

                if (success) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "User already exists or DB error.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
