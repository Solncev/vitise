package ru.kpfu.itis.group11501.vitise.util;

import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.Date;

/**
 * Created by Наталья on 04.04.2017.
 */
public class NewsAdapter extends CommonNews {

    private final News news;

    public NewsAdapter(News news) {
        this.news = news;
    }

    @Override
    public Long getSubscriptionsCount() {
        return null;
    }

    @Override
    public boolean isSubscribeStatus() {
        return false;
    }

    @Override
    public Long getId() {
        return news.getId();
    }

    @Override
    public String getTopic() {
        return news.getTopic();
    }

    @Override
    public String getText() {
        return news.getText();
    }

    @Override
    public User getAuthor() {
        return news.getAuthor();
    }

    @Override
    public Date getPubDate() {
        return news.getPubDate();
    }

    @Override
    public Date getEventDate() {
        return null;
    }

    @Override
    public boolean isEvent() {
        return false;
    }
}
