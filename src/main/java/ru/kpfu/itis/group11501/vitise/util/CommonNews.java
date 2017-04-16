package ru.kpfu.itis.group11501.vitise.util;

import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.Date;

/**
 * Created by Наталья on 03.04.2017.
 */
abstract public class CommonNews implements Comparable {

    abstract public Long getSubscriptionsCount();

    abstract public boolean isSubscribeStatus();

    abstract public Long getId();

    abstract public String getTopic();

    abstract public String getText();

    abstract public User getAuthor();

    abstract public Date getPubDate();

    abstract public Date getEventDate();

    abstract public boolean isEvent();

    @Override
    public int compareTo(Object o) {
        CommonNews commonNews = (CommonNews) o;
        if (this.getPubDate().getTime() > commonNews.getPubDate().getTime()) {
            return -1;
        } else if (this.getPubDate().getTime() < commonNews.getPubDate().getTime()) {
            return 1;
        }
        return 0;
    }
}
