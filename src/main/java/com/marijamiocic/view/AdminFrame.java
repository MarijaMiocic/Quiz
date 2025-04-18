package com.marijamiocic.view;

import javax.swing.*;

/**
 * GUI frame for administrative users.
 * Displays the admin interface of the application.
 */
public class AdminFrame extends JFrame {

    /**
     * Constructor for the admin frame.
     */
    public AdminFrame() {
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
