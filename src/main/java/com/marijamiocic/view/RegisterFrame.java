package com.marijamiocic.view;

import com.marijamiocic.controller.UserRepository;
import com.marijamiocic.model.User;
import com.marijamiocic.util.HashUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

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

    private void initComps() {
        emailField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");
    }

    private void layoutComps() {
        setLayout(new MigLayout("wrap 2", "[right][grow]"));
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerButton, "span 2, center");
    }

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
