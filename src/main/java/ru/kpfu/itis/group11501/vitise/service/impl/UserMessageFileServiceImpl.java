package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.UserMessageFile;
import ru.kpfu.itis.group11501.vitise.repository.UserMessageFileRepository;
import ru.kpfu.itis.group11501.vitise.service.UserMessageFileService;

/**
 * Created by Марат on 10.04.2017.
 */
@Service
public class UserMessageFileServiceImpl implements UserMessageFileService {
    @Autowired
    private final UserMessageFileRepository userMessageFileRepository;

    public UserMessageFileServiceImpl(UserMessageFileRepository userMessageFileRepository) {
        this.userMessageFileRepository = userMessageFileRepository;
    }

    @Override
    public UserMessageFile findOne(Long id) {
        return userMessageFileRepository.findOne(id);
    }

    @Override
    public UserMessageFile findByUserMessageId(Long userMessageId) {
        return userMessageFileRepository.findByUserMessageId(userMessageId);
    }

    @Override
    public void add(UserMessageFile userMessageFile) {
        userMessageFileRepository.save(userMessageFile);
    }

    @Override
    public void remove(UserMessageFile userMessageFile) {
        userMessageFileRepository.delete(userMessageFile);
    }
}
