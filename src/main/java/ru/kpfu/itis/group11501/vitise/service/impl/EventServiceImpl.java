package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.EventsUsers;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.EventRepository;
import ru.kpfu.itis.group11501.vitise.repository.EventsUsersRepository;
import ru.kpfu.itis.group11501.vitise.service.EventService;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventsUsersRepository eventsUsersRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EventsUsersRepository eventsUsersRepository) {
        this.eventRepository = eventRepository;
        this.eventsUsersRepository = eventsUsersRepository;
    }

    @Override
    public void add(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public void removeEvent(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public List<Event> getEvents(User currentUser) {
        List<Event> events = eventRepository.findByIsPublicOrderByPubDateDesc(true);
        this.addSubscriptionInfo(events, currentUser);
        return events;
    }

    @Override
    public List<Event> getUserEvents(User user, User currentUser) {
        List<Event> events = eventRepository.findByAuthorIdOrderByPubDateDesc(user.getId());
        this.addSubscriptionInfo(events, currentUser);
        return events;
    }

    @Override
    public void changeSubscribeStatus(Event event, User user) {
        EventsUsers eventsUsers = eventsUsersRepository.findOneByEventIdAndUserId(event.getId(), user.getId());
        if (eventsUsers == null) {
            eventsUsers = new EventsUsers();
            eventsUsers.setEvent(event);
            eventsUsers.setUser(user);
            eventsUsersRepository.save(eventsUsers);
        } else {
            eventsUsersRepository.delete(eventsUsers);
        }
    }

    @Override
    public boolean subscribeStatus(Event event, User user) {
        if (eventsUsersRepository.findOneByEventIdAndUserId(event.getId(), user.getId()) == null)
            return false;
        return true;
    }

    private List<Event> addSubscriptionInfo(List<Event> events, User currentUser) {
        for (Event event : events) {
            event.setSubscriptionsCount(eventsUsersRepository.countByEventId(event.getId()));
            if (subscribeStatus(event, currentUser))
                event.setSubscribeStatus(true);
        }
        return events;
    }
}