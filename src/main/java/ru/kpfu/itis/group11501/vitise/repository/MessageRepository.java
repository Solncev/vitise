package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.Message;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Марат on 25.03.2017.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRecipientIdAndSenderId(Long recipientId, Long senderId);

    @Query("select distinct m from Message m WHERE m.sender = :sender and m.recipient = :recipient" +
            " or m.sender = :recipient and m.recipient = :sender order by m.date desc")
    public List<Message> findAll(@Param("sender") User sender, @Param("recipient") User recipient);
}
