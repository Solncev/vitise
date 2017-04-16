package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
public interface CommunicationRelationRepository extends JpaRepository<CommunicationRelation, Long> {

    @Query("select c from CommunicationRelation c where (c.first = :user or c.second = :user)" +
            " and exists(select m from UserMessage m where m.communicationRelation = c.id)")
    List<CommunicationRelation> findAllByUser(@Param("user") User user);

    @Query("select c from CommunicationRelation c where c.first = :user and c.second = :current_user " +
            "or c.first = :current_user and c.second = :user")
    CommunicationRelation findOneDialog(@Param("user") User user, @Param("current_user") User currentUser);

    @Query("select count(relation) from CommunicationRelation relation WHERE (relation.first = :user or relation.second = :user) and " +
            "exists (select message from UserMessage message where " +
            "message.date > (select  log.date from UserMessagesReadingLog log where log.communicationRelation = relation and log.user = :user) " +
            "and message.communicationRelation = relation)")
    int countDialogsWithNewMessages(@Param("user") User user);
}
