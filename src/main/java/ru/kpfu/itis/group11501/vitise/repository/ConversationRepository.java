package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Conversation;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
