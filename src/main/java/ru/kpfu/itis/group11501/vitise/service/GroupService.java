package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Group;

import java.util.Map;

public interface GroupService {

    Group getGroup(Long id);

    Map<Long, String> getGroupsByCourse(int course);
}
