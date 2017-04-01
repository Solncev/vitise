package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Message;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Марат on 25.03.2017.
 */
public interface MessageService {
    List<Message> findAll(Long recipientId, Long senderId);

    Set<Long> findAllInterlocutors(User user);

    void add(Message message);
}
