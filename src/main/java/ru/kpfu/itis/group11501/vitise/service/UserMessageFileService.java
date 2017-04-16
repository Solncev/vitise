package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.UserMessageFile;

/**
 * Created by Марат on 10.04.2017.
 */
public interface UserMessageFileService {
    UserMessageFile findOne(Long id);

    UserMessageFile findByUserMessageId(Long userMessageId);

    void add(UserMessageFile userMessageFile);

    void remove(UserMessageFile userMessageFile);
}
