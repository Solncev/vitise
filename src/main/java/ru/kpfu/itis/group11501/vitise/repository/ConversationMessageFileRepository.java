package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessageFile;

/**
 * Created by Марат on 10.04.2017.
 */
public interface ConversationMessageFileRepository extends JpaRepository<ConversationMessageFile, Long> {
    ConversationMessageFile findByConversationMessageId(Long conversationMessageId);
}
