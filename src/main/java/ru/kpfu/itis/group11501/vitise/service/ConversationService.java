package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;
import ru.kpfu.itis.group11501.vitise.model.conversation.enums.ActiveStatusName;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationService {
    Conversation createConversation(String name, User creator, List<User> members);

    void saveConversation(Conversation conversation);

    Conversation getConversation(Long id);

    Conversation getConversationWithInfo(Long id, User user);

    List<Conversation> getConversations(User user);

    void addMember(Conversation conversation, User user);

    void memberChangeStatus(ConversationMember member, ActiveStatusName status);

    ConversationMember getMember(User currentUser, Conversation conversation);

    List<User> getAvailableUsers(Conversation conversation);

    void addMessage(ConversationMessage conversationMessage);

    void updateReadingLog(ConversationMember member);

    int newMessagesCount(User user);
}
