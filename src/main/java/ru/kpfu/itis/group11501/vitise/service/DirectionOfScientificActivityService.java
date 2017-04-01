package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivity;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Марат on 28.03.2017.
 */
public interface DirectionOfScientificActivityService {
    DirectionOfScientificActivity findOne(Long id);

    List<DirectionOfScientificActivity> findAll();

    List<DirectionOfScientificActivity> findByUser(User user);


}
