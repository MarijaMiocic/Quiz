package com.marijamiocic.view;

import java.util.EventListener;

/**
 * Listener interface for handling login events.
 */
public interface LoginListener extends EventListener {

    /**
     * Invoked when a login event occurs.
     * @param loginEvent The login event.
     */
    void loginEventOccurred(LoginEvent loginEvent);
}
