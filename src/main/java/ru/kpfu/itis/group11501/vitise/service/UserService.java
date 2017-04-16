package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;

import java.util.List;

public interface UserService {

    void add(User user);

    User getUser(String email);

    User getUser(Long id);

    User findOne(Long id);

    User getAdmin();

    List<Status> getStatus(User user);

    List getNewUsers();

    List<User> getNewUsers(Status status);

    void approveUser(Long id);

    void deleteUser(Long id);

    boolean isWorker(User user);

    List<User> getConfirmedUsers();

    void changeState(Long aLong);

    List<User> filterUserListByCriteria(Boolean isActive, List<StatusName> statusIdList, String[] args);
}
