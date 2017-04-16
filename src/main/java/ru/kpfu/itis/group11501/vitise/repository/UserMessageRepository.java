package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;

import java.util.Date;
import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    List<UserMessage> findAllByCommunicationRelationOrderByDateAsc(CommunicationRelation communicationRelation);


    @Query("select count(message) from UserMessage message WHERE message.date > :readingLogDate and message.communicationRelation = :relation")
    int newMessagesCount(@Param("relation") CommunicationRelation relation, @Param("readingLogDate") Date readingLogDate);

}


