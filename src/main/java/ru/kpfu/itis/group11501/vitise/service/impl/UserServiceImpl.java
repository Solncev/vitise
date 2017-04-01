package ru.kpfu.itis.group11501.vitise.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UsersStatus;
import ru.kpfu.itis.group11501.vitise.repository.UserRepository;
import ru.kpfu.itis.group11501.vitise.repository.UsersStatusRepository;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UsersStatusRepository usersStatusRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UsersStatusRepository usersStatusRepository) {
        this.userRepository = userRepository;
        this.usersStatusRepository = usersStatusRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public List<Status> getStatus(User user) {
        List<Status> statuses = new ArrayList<>();
        List<UsersStatus> usersStatuses = usersStatusRepository.findAllStatusIdByUserId(user.getId());
        for (UsersStatus usersStatus : usersStatuses) {
            statuses.add(usersStatus.getStatus());
        }
        return statuses;
    }

    @Override
    public List<User> getNewUsers() {
        return userRepository.findAllByIsActive(null);
    }

    @Override
    public void approveUser(Long id) {
        User user = getUser(id);
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(id);
    }

    @Override
    public boolean isWorker(User user) {
        List<Status> statuses = getStatus(user);
        for (Status status : statuses) {
            if (status.getName().equals("WORKER")) {
                return true;
            }
        }
        return false;
    }
}
