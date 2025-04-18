package com.marijamiocic.view;

import java.util.EventListener;

/**
 * Listener interface for menu bar item selection.
 */
public interface MenuBarListener extends EventListener {

    /**
     * Triggered when a menu item is selected.
     *
     * @param command the action command of the selected item
     */
    void menuBarEventOccurred(String command);
}
