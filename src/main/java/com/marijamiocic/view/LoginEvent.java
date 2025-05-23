package com.marijamiocic.view;

import com.marijamiocic.model.User;

import java.util.EventObject;

/**
 * Represents a login event triggered by user interaction.
 */
public class LoginEvent extends EventObject {

    private User user;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @param user Logged user
     * @throws IllegalArgumentException if source is null
     */
    public LoginEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "LoginEvent{" +
                "user=" + user +
                '}';
    }
}
