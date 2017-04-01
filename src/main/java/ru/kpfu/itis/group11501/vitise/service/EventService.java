package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface EventService {

    void add(Event event);

    Event getEvent(Long id);

    void removeEvent(Event event);

    List<Event> getEvents(User currentUser);

    List<Event> getUserEvents(User user, User currentUser);

    void changeSubscribeStatus(Event event, User user);

    boolean subscribeStatus(Event event, User user);

}
