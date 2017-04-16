package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;
import ru.kpfu.itis.group11501.vitise.model.UserMessagesReadingLog;
import ru.kpfu.itis.group11501.vitise.repository.CommunicationRelationRepository;
import ru.kpfu.itis.group11501.vitise.repository.UserMessageRepository;
import ru.kpfu.itis.group11501.vitise.repository.UserMessagesReadingLogRepository;
import ru.kpfu.itis.group11501.vitise.service.CommunicationRelationService;
import ru.kpfu.itis.group11501.vitise.service.UserMessageService;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
@Service
public class CommunicationRelationServiceImpl implements CommunicationRelationService {
    private final CommunicationRelationRepository communicationRelationRepository;
    private final UserMessageService userMessageService;
    private final UserMessagesReadingLogRepository readingLogRepository;
    private final UserMessageRepository messageRepository;

    @Autowired
    public CommunicationRelationServiceImpl(CommunicationRelationRepository communicationRelationRepository,
                                            UserMessageService usersMessagesService,
                                            UserMessagesReadingLogRepository readingLogRepository,
                                            UserMessageRepository messageRepository) {
        this.communicationRelationRepository = communicationRelationRepository;
        this.userMessageService = usersMessagesService;
        this.readingLogRepository = readingLogRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void createCommunicationRelations(User user, User currentUser) {
        CommunicationRelation communicationRelation = new CommunicationRelation();
        communicationRelation.setFirst(currentUser);
        communicationRelation.setSecond(user);
        communicationRelationRepository.save(communicationRelation);

        this.updateReadingLog(communicationRelation, communicationRelation.getFirst());
        this.updateReadingLog(communicationRelation, communicationRelation.getSecond());
    }

    @Override
    public List<CommunicationRelation> getAll(User user) {
        List<CommunicationRelation> relations = communicationRelationRepository.findAllByUser(user);
        for (CommunicationRelation relation : relations) {
            relation.setCurrentUser(user);
            UserMessagesReadingLog readingLog = this.getReadingLog(relation, user);
            relation.setNewMessagesCount(
                    messageRepository.newMessagesCount(relation, readingLog.getDate()));
            relation.setReadingLog(readingLog);
            List<UserMessage> messages = userMessageService.getAll(relation);
            if (messages.size() > 0)
                relation.setLastMessage(messages.get(messages.size() - 1));
        }
        return relations;
    }

    @Override
    public CommunicationRelation getOne(Long id) {
        try {
            return communicationRelationRepository.findOne(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public CommunicationRelation getOne(User user, User currentUser) {
        try {
            return communicationRelationRepository.findOneDialog(user, currentUser);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public CommunicationRelation getOneWithInfo(Long id, User user) {
        CommunicationRelation relation = this.getOne(id);
        relation.setMessages(userMessageService.getAll(relation));
        relation.setReadingLog(this.getReadingLog(relation, user));
        return relation;
    }

    @Override
    public void updateReadingLog(CommunicationRelation relation, User user) {
        UserMessagesReadingLog readingLog = this.getReadingLog(relation, user);
        if (readingLog == null) {
            readingLog = new UserMessagesReadingLog();
            readingLog.setCommunicationRelation(relation);
            readingLog.setUser(user);
        } else
            readingLog.setDate(new Date(System.currentTimeMillis()));
        readingLogRepository.save(readingLog);
    }

    @Override
    public int newMessagesCount(User user) {
        return communicationRelationRepository.countDialogsWithNewMessages(user);
    }

    private UserMessagesReadingLog getReadingLog(CommunicationRelation relation, User user) {
        return readingLogRepository.findOneByCommunicationRelationIdAndUserId(relation.getId(), user.getId());
    }
}
