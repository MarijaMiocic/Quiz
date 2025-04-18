package com.marijamiocic.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu bar for the quiz application.
 * Provides navigation to different sections like play, statistics, and about.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private JMenu playMenu;
    private JMenu settingsMenu;

    private MenuBarListener menuBarListener;

    private JMenuItem generalKnowledge;
    private JMenuItem statistics;
    private JMenuItem about;

    /**
     * Constructor for the menu bar.
     */
    public MenuBar() {
        initComps();
        layoutComps();
        activateMenuBar();
    }

    /**
     * Initializes menu items and sets action commands.
     */
    private void initComps() {
        playMenu = new JMenu("Play");
        generalKnowledge = new JMenuItem("General Knowledge");

        generalKnowledge.setActionCommand("generalKnowledge");

        settingsMenu = new JMenu("Settings");
        statistics = new JMenuItem("Statistics");
        about = new JMenuItem("About");

        statistics.setActionCommand("statistics");
        about.setActionCommand("about");
    }

    /**
     * Adds menu items to the menu bar.
     */
    private void layoutComps() {
        playMenu.add(generalKnowledge);
        add(playMenu);

        settingsMenu.add(statistics);
        settingsMenu.add(about);
        add(settingsMenu);
    }

    /**
     * Adds action listeners to menu items.
     */
    private void activateMenuBar() {
        generalKnowledge.addActionListener(this);
        statistics.addActionListener(this);
        about.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generalKnowledge) {
            if (menuBarListener != null) {

            }
        }

        if (e.getSource() == statistics) {
            if (menuBarListener != null) {

            }
        }

        if (e.getSource() == about) {
            if (menuBarListener != null) {

            }
        }
    }
}
