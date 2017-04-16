package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;

import java.util.List;

/**
 * Created by Bogdan Popov on 09.04.2017.
 */
public interface AdminService {
    List<StatusName> getPermissionsForArchive();

    boolean checkPermissionsForArchive(Long id);
}
