package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Event;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.NewsRepository;
import ru.kpfu.itis.group11501.vitise.service.CommonNewsService;
import ru.kpfu.itis.group11501.vitise.service.EventService;
import ru.kpfu.itis.group11501.vitise.util.CommonNews;
import ru.kpfu.itis.group11501.vitise.util.EventsAdapter;
import ru.kpfu.itis.group11501.vitise.util.NewsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Наталья on 04.04.2017.
 */
@Service
public class CommonNewsServiceImpl implements CommonNewsService {
    private final EventService eventService;
    private final NewsRepository newsRepository;

    public CommonNewsServiceImpl(EventService eventService, NewsRepository newsRepository) {
        this.eventService = eventService;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<CommonNews> getAllCommonNews(User user) {
        List<Event> events = eventService.getEvents(user);
        List<News> newses = newsRepository.getAllByIsPersonal(false);
        List<CommonNews> commonNewses = new ArrayList<>();
        for (Event e : events) {
            commonNewses.add(new EventsAdapter(e));
        }
        for (News n : newses) {
            commonNewses.add(new NewsAdapter(n));
        }
        Collections.sort(commonNewses);
        return commonNewses;
    }
}
