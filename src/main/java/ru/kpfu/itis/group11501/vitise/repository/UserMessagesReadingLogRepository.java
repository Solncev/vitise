package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.UserMessagesReadingLog;

/**
 * Author: Svintenok Kate
 * Date: 09.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface UserMessagesReadingLogRepository extends JpaRepository<UserMessagesReadingLog, Long> {

    UserMessagesReadingLog findOneByCommunicationRelationIdAndUserId(Long relationId, Long userId);
}
