package com.marijamiocic.view;

import com.marijamiocic.model.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private LoginFrame loginFrame;
    private User user;
    private JLabel userNameLabel;
    private MenuBar menuBar;
    private JLabel logo;
    private JLabel title;


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

    private void activateLoginListener() {
        loginFrame.setLoginListener(new LoginListener() {
            @Override
            public void loginEventOccurred(LoginEvent loginEvent) {
                user = loginEvent.getUser();
//                initComps();
//                layoutComps();
                setVisible(true);
//                activateComps();
                loginFrame.dispose();
            }
        });
        loginFrame.activateComps();
    }

    private void initComps() {
        menuBar = new MenuBar();
        title = new JLabel("Quiz");
        title.setFont(new Font("SansSerif", Font.BOLD, 70));
        logo = new JLabel();
        logo.setIcon(new ImageIcon("src/main/resources/logo.png"));
    }

    private void layoutComps() {
        setLayout(new MigLayout("wrap 1", "", "[][]"));
        add(menuBar, "w 800, h 50, wrap");
        add(logo, "gaptop 150, split 2, center, span");
        add(title, "gaptop 150, center, wrap");
    }

    private void activateComps() {

    }
}
