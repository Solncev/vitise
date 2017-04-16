package ru.kpfu.itis.group11501.vitise.repository;

import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Bogdan Popov on 07.04.2017.
 */
public interface UserRepositoryCustom {
    List<User> findAllByIsActiveAndStatusId(Boolean isActive, List<Long> idStatusList, String[] args);
}
