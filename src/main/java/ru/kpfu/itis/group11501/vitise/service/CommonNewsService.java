package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.util.CommonNews;

import java.util.List;

/**
 * Created by Наталья on 04.04.2017.
 */
public interface CommonNewsService {
    List<CommonNews> getAllCommonNews(User user);
}
