package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Наталья on 06.04.2017.
 */
public interface CommunicationRelationService {

    void createCommunicationRelations(User user, User currentUser);

    List<CommunicationRelation> getAll(User user);

    CommunicationRelation getOne(Long id);

    CommunicationRelation getOne(User user, User currentUser);

    CommunicationRelation getOneWithInfo(Long id, User user);

    void updateReadingLog(CommunicationRelation relation, User user);

    int newMessagesCount(User user);

}
