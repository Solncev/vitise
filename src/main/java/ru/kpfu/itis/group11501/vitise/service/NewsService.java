package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Марат on 22.03.2017.
 */
public interface NewsService {
    void add(News news);

    void remove(News news);

    News get(Long id);

    List<News> getAllByAuthor(User author);
}
