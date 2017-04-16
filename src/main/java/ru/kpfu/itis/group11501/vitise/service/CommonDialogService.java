package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.util.messages.CommonDialog;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface CommonDialogService {
    List<CommonDialog> getDialogs(User user);

    int newMessagesCount(User user);
}
