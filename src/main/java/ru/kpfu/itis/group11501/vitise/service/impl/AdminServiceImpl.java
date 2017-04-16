package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.service.AdminService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan Popov on 09.04.2017.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    @Autowired
    public AdminServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<StatusName> getPermissionsForArchive() {
        List<StatusName> statusNames = new ArrayList<>();
        statusNames.add(StatusName.STUDENT);
        statusNames.add(StatusName.WORKER);
        return statusNames;
    }

    @Override
    public boolean checkPermissionsForArchive(Long id) {
        for (StatusName statusName : getPermissionsForArchive()) {
            if (id.equals((long) statusName.ordinal())) {
                return true;
            }
        }
        return false;
    }
}
