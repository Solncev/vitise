package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMember;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationMemberRepository extends JpaRepository<ConversationMember, Long> {

    List<ConversationMember> findByConversationIdAndActiveStatus(Long id, int activeStatus);

    List<ConversationMember> findByUserIdAndActiveStatus(Long id, int activeStatus);

    List<ConversationMember> findByUserId(Long id);

    ConversationMember findOneByUserIdAndConversationId(Long userId, Long conversationId);
}
