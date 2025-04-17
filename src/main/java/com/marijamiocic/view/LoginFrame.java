package com.marijamiocic.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JLabel logo;
    private JLabel title;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton login;
    private JButton register;
    private LoginListener loginListener;

    /**
     * Constructor for the login frame.
     */
    public LoginFrame() {
        super("Quiz Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        Image logo = Toolkit.getDefaultToolkit().getImage("src/main/resources/logo.png");
        setIconImage(logo);

        initComps();
        layoutComps();
    }

    /**
     * Sets the login listener for the login frame.
     *
     * @param loginListener
     */
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    /**
     * Initializes the components for the login frame.
     */
    private void initComps() {
        title = new JLabel("Quiz");
        title.setFont(new Font("SansSerif", Font.BOLD, 50));
        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/main/resources/logo.png"));
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        login = new JButton("Login");
        register = new JButton("Register");
    }

    /**
     * Configures the layout of the components in the login frame.
     * Adds labels and text fields to the layout.
     */
    private void layoutComps() {
        setLayout(new MigLayout("insets 180 50 50 50, center", "", "[]20[][]"));
        add(logo, "split 2, center, span");
        add(title, "center, wrap");
        add(new JLabel("Username: "), "split 2, center");
        add(usernameField, "split 2, center, wrap");
        add(new JLabel("Password: "), "split 2, center, gapafter 10");
        add(passwordField, "split 2, center, wrap");
        add(login, "split 2, center, span, gapafter 40");
        add(register);
    }

    /**
     * Activates the components by adding action listeners to buttons.
     * Defines the behavior of the buttons when clicked.
     */
    public void activateComps() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter your username!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.equals("")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter your password!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
            }
        });
    }
}
