package ru.kpfu.itis.group11501.vitise.util.messages;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public class UserMessageAdapter implements CommonMessage {

    private final UserMessage message;

    public UserMessageAdapter(UserMessage message) {
        this.message = message;
    }

    @Override
    public Long getId() {
        return message.getId();
    }

    @Override
    public Date getDate() {
        return message.getDate();
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }

    @Override
    public User getUser() {
        return message.getUser();
    }
}