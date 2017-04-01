package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.ConversationMessage;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 28.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, Long> {
    List<ConversationMessage> findAllByConversationIdOrderByDateDesc(Long id);
}
