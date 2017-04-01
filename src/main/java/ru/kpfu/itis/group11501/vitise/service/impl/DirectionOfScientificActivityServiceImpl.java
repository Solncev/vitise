package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivity;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivityUser;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.DirectionOfScientificActivityRepository;
import ru.kpfu.itis.group11501.vitise.repository.DirectionOfScientificActivityUserRepository;
import ru.kpfu.itis.group11501.vitise.service.DirectionOfScientificActivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Марат on 28.03.2017.
 */
@Service
public class DirectionOfScientificActivityServiceImpl implements DirectionOfScientificActivityService {
    private final DirectionOfScientificActivityRepository directionOfScientificActivityRepository;
    private final DirectionOfScientificActivityUserRepository directionOfScientificActivityUserRepository;

    @Autowired
    public DirectionOfScientificActivityServiceImpl(DirectionOfScientificActivityRepository directionOfScientificActivityRepository, DirectionOfScientificActivityUserRepository directionOfScientificActivityUserRepository) {
        this.directionOfScientificActivityRepository = directionOfScientificActivityRepository;
        this.directionOfScientificActivityUserRepository = directionOfScientificActivityUserRepository;
    }

    @Override
    public DirectionOfScientificActivity findOne(Long id) {
        return directionOfScientificActivityRepository.findOne(id);
    }

    @Override
    public List<DirectionOfScientificActivity> findAll() {
        return directionOfScientificActivityRepository.findAll();
    }

    @Override
    public List<DirectionOfScientificActivity> findByUser(User user) {
        List<DirectionOfScientificActivity> directionOfScientificActivities = new ArrayList<>();
        List<DirectionOfScientificActivityUser> directions = directionOfScientificActivityUserRepository.findDirectionOfScientificActivityByUserId(user.getId());
        for (DirectionOfScientificActivityUser d : directions) {
            directionOfScientificActivities.add(directionOfScientificActivityRepository.findOne(d.getDirectionOfScientificActivity().getId()));
        }
        return directionOfScientificActivities;
    }
}
