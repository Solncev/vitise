package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;
import ru.kpfu.itis.group11501.vitise.repository.UserMessageRepository;
import ru.kpfu.itis.group11501.vitise.service.UserMessageService;

import java.util.Date;
import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {
    private final UserMessageRepository userMessageRepository;

    @Autowired
    public UserMessageServiceImpl(UserMessageRepository userMessageRepository) {
        this.userMessageRepository = userMessageRepository;
    }

    @Override
    public List<UserMessage> getAll(CommunicationRelation communicationRelation) {
        return userMessageRepository.findAllByCommunicationRelationOrderByDateAsc(communicationRelation);
    }

    @Override
    public void addUsersMessages(UserMessage usersMessages) {
        usersMessages.setDate(new Date(System.currentTimeMillis()));
        userMessageRepository.save(usersMessages);
    }
}
