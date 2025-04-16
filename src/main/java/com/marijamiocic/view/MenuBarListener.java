package com.marijamiocic.view;

import java.util.EventListener;

public interface MenuBarListener extends EventListener {

    void menuBarEventOccurred(String command);
}
