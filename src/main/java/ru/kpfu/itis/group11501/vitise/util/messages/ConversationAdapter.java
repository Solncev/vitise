package ru.kpfu.itis.group11501.vitise.util.messages;

import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public class ConversationAdapter implements CommonDialog {

    private final Conversation conversation;

    public ConversationAdapter(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public Long getId() {
        return conversation.getId();
    }

    @Override
    public String getName() {
        return conversation.getName();
    }

    @Override
    public CommonMessage getLastMessage() {
        if (conversation.getLastMessage() != null)
            return new ConversationMessageAdapter(conversation.getLastMessage());
        return null;
    }

    @Override
    public boolean isConversation() {
        return true;
    }

    @Override
    public Date getCreationDate() {
        return conversation.getCreationDate();
    }

    @Override
    public int getNewMessagesCount() {
        return conversation.getNewMessagesCount();
    }

    @Override
    public Date getReadingLogDate() {
        return conversation.getCurrentMember().getMessagesReadingLog().getDate();
    }
}