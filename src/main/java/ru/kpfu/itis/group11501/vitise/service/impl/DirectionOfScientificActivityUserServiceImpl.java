package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivityUser;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.DirectionOfScientificActivityRepository;
import ru.kpfu.itis.group11501.vitise.repository.DirectionOfScientificActivityUserRepository;
import ru.kpfu.itis.group11501.vitise.service.DirectionOfScientificActivityUserService;

import java.util.List;

/**
 * Created by Марат on 28.03.2017.
 */
@Service
public class DirectionOfScientificActivityUserServiceImpl implements DirectionOfScientificActivityUserService {
    private final DirectionOfScientificActivityUserRepository directionOfScientificActivityUserRepository;
    private final DirectionOfScientificActivityRepository directionOfScientificActivityRepository;

    @Autowired
    public DirectionOfScientificActivityUserServiceImpl(DirectionOfScientificActivityUserRepository directionOfScientificActivityUserRepository, DirectionOfScientificActivityRepository directionOfScientificActivityRepository) {
        this.directionOfScientificActivityUserRepository = directionOfScientificActivityUserRepository;
        this.directionOfScientificActivityRepository = directionOfScientificActivityRepository;
    }

    @Override
    public void add(User user, List<String> directions) {
        deleteByUser(user);
        for (String direction : directions) {
            DirectionOfScientificActivityUser directionOfScientificActivityUser = new DirectionOfScientificActivityUser();
            directionOfScientificActivityUser.setDirectionOfScientificActivity(directionOfScientificActivityRepository.getByName(direction));
            directionOfScientificActivityUser.setUser(user);
            try {
                directionOfScientificActivityUserRepository.save(directionOfScientificActivityUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteByUser(User user) {
        List<DirectionOfScientificActivityUser> directionOfScientificActivityUsers =
                directionOfScientificActivityUserRepository.findDirectionOfScientificActivityByUserId(user.getId());
        for (DirectionOfScientificActivityUser d : directionOfScientificActivityUsers) {
            directionOfScientificActivityUserRepository.delete(d);
        }
    }
}
