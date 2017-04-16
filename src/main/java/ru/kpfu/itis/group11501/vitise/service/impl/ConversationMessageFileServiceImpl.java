package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessageFile;
import ru.kpfu.itis.group11501.vitise.repository.ConversationMessageFileRepository;
import ru.kpfu.itis.group11501.vitise.service.ConversationMessageFileService;

/**
 * Created by Марат on 10.04.2017.
 */
@Service
public class ConversationMessageFileServiceImpl implements ConversationMessageFileService {
    @Autowired
    private final ConversationMessageFileRepository conversationMessageFileRepository;

    public ConversationMessageFileServiceImpl(ConversationMessageFileRepository conversationMessageFileRepository) {
        this.conversationMessageFileRepository = conversationMessageFileRepository;
    }

    @Override
    public ConversationMessageFile findOne(Long id) {
        return conversationMessageFileRepository.findOne(id);
    }

    @Override
    public ConversationMessageFile findByConversationMessageId(Long conversationMessageId) {
        return conversationMessageFileRepository.findByConversationMessageId(conversationMessageId);
    }

    @Override
    public void add(ConversationMessageFile conversationMessageFile) {
        conversationMessageFileRepository.save(conversationMessageFile);
    }

    @Override
    public void remove(ConversationMessageFile conversationMessageFile) {
        conversationMessageFileRepository.delete(conversationMessageFile);
    }
}
