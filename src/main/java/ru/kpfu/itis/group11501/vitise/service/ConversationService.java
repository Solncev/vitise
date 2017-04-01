package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Conversation;
import ru.kpfu.itis.group11501.vitise.model.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.ConversationMessage;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationService {

    void saveConversation(Conversation conversation);
    Conversation createConversation(String name, User creator, List<User> members);
    void addMember(Conversation conversation, User user);
    void addMessage(ConversationMessage conversationMessage);
    Conversation getConversation(Long id);
    List<Conversation> getConversations(User user);
    ConversationMember getMember(User currentUser, Conversation conversation);
    void deleteMember(ConversationMember member);
    void addMember(ConversationMember member);
    List<User> getAvailableUsers(Conversation conversation);
}
