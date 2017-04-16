package ru.kpfu.itis.group11501.vitise.util;

import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.Date;

/**
 * Created by Наталья on 04.04.2017.
 */
public class EventsAdapter extends CommonNews {
    private final Event event;

    public EventsAdapter(Event event) {
        this.event = event;
    }

    @Override
    public Long getSubscriptionsCount() {
        return event.getSubscriptionsCount();
    }

    @Override
    public boolean isSubscribeStatus() {
        return event.isSubscribeStatus();
    }

    @Override
    public Long getId() {
        return event.getId();
    }

    @Override
    public String getTopic() {
        return event.getName();
    }

    @Override
    public String getText() {
        return event.getText();
    }

    @Override
    public User getAuthor() {
        return event.getAuthor();
    }

    @Override
    public Date getPubDate() {
        return event.getPubDate();
    }

    @Override
    public Date getEventDate() {
        return event.getEventDate();
    }

    @Override
    public boolean isEvent() {
        return true;
    }
}
