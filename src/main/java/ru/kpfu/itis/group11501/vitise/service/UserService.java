package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    User getUser(String email);

    User getUser(Long id);

    User findOne(Long id);

    List<Status> getStatus(User user);

    List getNewUsers();

    void approveUser(Long id);

    void deleteUser(Long id);

    boolean isWorker(User user);
}
