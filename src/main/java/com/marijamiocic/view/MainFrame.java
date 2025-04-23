package com.marijamiocic.view;

import com.marijamiocic.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window shown after successful login.
 * Handles GUI display and user session.
 */
public class MainFrame extends JFrame {

    private LoginFrame loginFrame;
    private User user;
    private JLabel userNameLabel;
    private MenuBar menuBar;
    private JLabel logo;
    private JLabel title;

    /**
     * Constructor for the main frame.
     */
    public MainFrame() {
        super("Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        Image logo = Toolkit.getDefaultToolkit().getImage("src/main/resources/logo.png");
        setIconImage(logo);

        loginFrame = new LoginFrame();

        initComps();
        layoutComps();
        activateComps();

        activateLoginListener();
    }

    /**
     * Activates the login listener that listens for login success events.
     */
    private void activateLoginListener() {
        loginFrame.setLoginListener(new LoginListener() {
            @Override
            public void loginEventOccurred(LoginEvent loginEvent) {
                user = loginEvent.getUser();
                updateUserInfo();
//                initComps();
//                layoutComps();
                setVisible(true);
//                activateComps();
                loginFrame.dispose();
            }
        });
        loginFrame.activateComps();
    }

    /**
     * Initializes the components for the main frame.
     */
    private void initComps() {
        menuBar = new MenuBar();
        title = new JLabel("Quiz");
        title.setFont(new Font("SansSerif", Font.BOLD, 70));
        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/main/resources/logo.png"));

        userNameLabel = new JLabel("");
        userNameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    }

    /**
     * Configures the layout of the components in the main frame using MigLayout.
     * Adds labels and text fields to the layout.
     */
    private void layoutComps() {
        setLayout(new MigLayout("wrap 1", "", "[][]"));
        add(menuBar, "w 800, h 50, wrap");
        add(userNameLabel, "align right, gapbottom 10, wrap");
        add(logo, "gaptop 150, split 2, center, span");
        add(title, "gaptop 150, center, wrap");
    }

    /**
     * Activates the components by adding action listeners.
     * Defines the behavior of the components when clicked.
     */
    private void activateComps() {

    }

    /**
     * Updates the username label with the currently logged-in user's username.
     */
    private void updateUserInfo() {
        if (user != null) {
            userNameLabel.setText("Hello " + user.getUsername() + "!");
            userNameLabel.revalidate();
            userNameLabel.repaint();
        }
    }
}
