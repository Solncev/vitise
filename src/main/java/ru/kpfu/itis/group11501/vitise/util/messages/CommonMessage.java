package ru.kpfu.itis.group11501.vitise.util.messages;

import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface CommonMessage {

    Long getId();

    Date getDate();

    String getMessage();

    User getUser();
}
