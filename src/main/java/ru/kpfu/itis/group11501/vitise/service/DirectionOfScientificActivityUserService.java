package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Марат on 28.03.2017.
 */
public interface DirectionOfScientificActivityUserService {
    void add(User user, List<String> directions);

    void deleteByUser(User user);
}
