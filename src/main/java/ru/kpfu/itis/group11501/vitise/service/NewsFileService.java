package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.NewsFile;

/**
 * Created by Марат on 05.04.2017.
 */
public interface NewsFileService {
    NewsFile findOne(Long id);

    NewsFile findByFileName(String filename);

    NewsFile findByNewsId(Long newsId);

    void add(NewsFile newsFile);

    void remove(NewsFile newsFile);
}
