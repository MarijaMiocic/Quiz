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
     * Setts menu bar listener.
     * @param menuBarListener Listener for menu bar
     */
    public void setMenuBarListener(MenuBarListener menuBarListener) {
        this.menuBarListener = menuBarListener;
    }

    /**
     * Initializes menu items.
     */
    private void initComps() {
        playMenu = new JMenu("Play");
        generalKnowledge = new JMenuItem("General Knowledge");

        settingsMenu = new JMenu("Settings");
        statistics = new JMenuItem("Statistics");
        about = new JMenuItem("About");
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
     * Adds action listeners to menu items and sets action commands.
     */
    private void activateMenuBar() {
        generalKnowledge.addActionListener(this);
        generalKnowledge.setActionCommand("General knowledge");
        statistics.addActionListener(this);
        statistics.setActionCommand("Statistics");
        about.addActionListener(this);
        about.setActionCommand("About");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generalKnowledge) {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(generalKnowledge.getActionCommand());
            }
        }

        if (e.getSource() == statistics) {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(statistics.getActionCommand());
            }
        }

        if (e.getSource() == about) {
            if (menuBarListener != null) {
                menuBarListener.menuBarEventOccurred(about.getActionCommand());
            }
        }
    }
}
