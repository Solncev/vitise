package ru.kpfu.itis.group11501.vitise.util.messages;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public class ConversationMessageAdapter implements CommonMessage {

    private final ConversationMessage message;

    public ConversationMessageAdapter(ConversationMessage message) {
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
        return message.getText();
    }

    @Override
    public User getUser() {
        return message.getUser();
    }
}