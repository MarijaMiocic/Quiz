package com.marijamiocic.view;

import java.util.EventListener;

public interface LoginListener extends EventListener {

    void loginEventOccurred(LoginEvent loginEvent);
}
