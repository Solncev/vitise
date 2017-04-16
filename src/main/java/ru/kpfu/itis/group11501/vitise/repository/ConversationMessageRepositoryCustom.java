package ru.kpfu.itis.group11501.vitise.repository;

import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;

import java.util.Date;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 05.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationMessageRepositoryCustom {
    List<ConversationMessage> findAllByConversationAndDatesList(Conversation conversation, List<Date> dates);

    int newMessagesCount(Conversation conversation, List<Date> dates, Date logDate);
}
