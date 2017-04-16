package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.service.CommonDialogService;
import ru.kpfu.itis.group11501.vitise.service.CommunicationRelationService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;
import ru.kpfu.itis.group11501.vitise.util.messages.CommonDialog;
import ru.kpfu.itis.group11501.vitise.util.messages.CommunicationRelationAdapter;
import ru.kpfu.itis.group11501.vitise.util.messages.ConversationAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
@Service
public class CommonDialogServiceImpl implements CommonDialogService {

    private final ConversationService conversationService;
    private final CommunicationRelationService relationService;

    @Autowired
    public CommonDialogServiceImpl(ConversationService conversationService, CommunicationRelationService relationService) {
        this.conversationService = conversationService;
        this.relationService = relationService;
    }

    @Override
    public List<CommonDialog> getDialogs(User user) {
        List<CommonDialog> dialogs = new ArrayList<>();
        for (Conversation conversation : conversationService.getConversations(user)) {
            dialogs.add(new ConversationAdapter(conversation));
        }
        for (CommunicationRelation relation : relationService.getAll(user)) {
            dialogs.add(new CommunicationRelationAdapter(relation));
        }
        Collections.sort(dialogs);
        return dialogs;
    }

    @Override
    public int newMessagesCount(User user) {
        return this.relationService.newMessagesCount(user) + conversationService.newMessagesCount(user);
    }
}