package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface ConversationMemberRepository extends JpaRepository<ConversationMember, Long> {
    List<ConversationMember> findByConversationIdAndIsActive(Long id, Boolean isActive);
    List<ConversationMember> findByUserIdAndIsActive(Long id, boolean isActive);
    ConversationMember findOneByUserIdAndConversationId(Long userId, Long conversationId);
}
