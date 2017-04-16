package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;

import java.util.Date;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 28.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, Long>, ConversationMessageRepositoryCustom {
    List<ConversationMessage> findAllByConversationIdOrderByDateDesc(Long id);


    @Query("select message from ConversationMessage message WHERE message.date < :date and message.conversation = :conversation")
    List<ConversationMessage> findAllByConversationBeforeDate(@Param("conversation") Conversation conversation, @Param("date") Date date);
}
