package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.conversation.MessagesReadingLog;

/**
 * Author: Svintenok Kate
 * Date: 01.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface MessagesReadingLogRepository extends JpaRepository<MessagesReadingLog, Long> {
    MessagesReadingLog findOneByMemberId(Long id);
}
