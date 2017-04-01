package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UsersStatus;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.repository.StatusRepository;
import ru.kpfu.itis.group11501.vitise.repository.UsersStatusRepository;
import ru.kpfu.itis.group11501.vitise.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final UsersStatusRepository usersStatusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository, UsersStatusRepository usersStatusRepository) {
        this.statusRepository = statusRepository;
        this.usersStatusRepository = usersStatusRepository;
    }

    @Override
    public Status getStatus(StatusName statusName) {
        return statusRepository.findOneByName(String.valueOf(statusName));
    }

    @Override
    public void addUsersStatus(User user, StatusName statusName) {
        UsersStatus usersStatus = new UsersStatus();
        usersStatus.setUser(user);
        usersStatus.setStatus(getStatus(statusName));
        usersStatusRepository.save(usersStatus);
    }
}
