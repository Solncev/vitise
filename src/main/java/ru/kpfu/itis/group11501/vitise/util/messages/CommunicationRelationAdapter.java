package ru.kpfu.itis.group11501.vitise.util.messages;

import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public class CommunicationRelationAdapter implements CommonDialog {
    private final CommunicationRelation relation;

    public CommunicationRelationAdapter(CommunicationRelation relation) {
        this.relation = relation;
    }

    @Override
    public Long getId() {
        return relation.getId();
    }

    @Override
    public String getName() {
        if (relation.getCurrentUser().getId() == relation.getFirst().getId())
            return relation.getSecond().getFullName();
        return relation.getFirst().getFullName();
    }

    @Override
    public CommonMessage getLastMessage() {
        if (relation.getLastMessage() != null)
            return new UserMessageAdapter(relation.getLastMessage());
        return null;
    }

    @Override
    public boolean isConversation() {
        return false;
    }

    @Override
    public Date getCreationDate() {
        return relation.getCreationDate();
    }

    @Override
    public int getNewMessagesCount() {
        return relation.getNewMessagesCount();
    }

    @Override
    public Date getReadingLogDate() {
        return relation.getReadingLog().getDate();
    }
}