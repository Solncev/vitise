package ru.kpfu.itis.group11501.vitise.service;


import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;

public interface StatusService {

    Status getStatus(StatusName statusName);

    void addUsersStatus(User user, StatusName statusName);
}
