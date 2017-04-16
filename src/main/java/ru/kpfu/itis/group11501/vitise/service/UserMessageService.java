package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;

import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
public interface UserMessageService {
    List<UserMessage> getAll(CommunicationRelation communicationRelation);

    void addUsersMessages(UserMessage usersMessages);
}
