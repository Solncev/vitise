package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessageFile;

/**
 * Created by Марат on 10.04.2017.
 */
public interface ConversationMessageFileService {
    ConversationMessageFile findOne(Long id);

    ConversationMessageFile findByConversationMessageId(Long conversationMessageId);

    void add(ConversationMessageFile conversationMessageFile);

    void remove(ConversationMessageFile conversationMessageFile);
}
