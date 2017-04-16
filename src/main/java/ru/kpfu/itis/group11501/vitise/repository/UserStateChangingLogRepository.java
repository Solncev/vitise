package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.conversation.UserStateChangingLog;

import java.util.Date;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 01.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface UserStateChangingLogRepository extends JpaRepository<UserStateChangingLog, Long> {

    @Query("select stateChangingLog.date from UserStateChangingLog stateChangingLog " +
            "WHERE stateChangingLog.member = :member ORDER BY stateChangingLog.date ASC")
    List<Date> findAllDateByMember(@Param("member") ConversationMember member);
}
