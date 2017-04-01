package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Group;
import ru.kpfu.itis.group11501.vitise.model.Semester;
import ru.kpfu.itis.group11501.vitise.repository.GroupRepository;
import ru.kpfu.itis.group11501.vitise.service.GroupService;
import ru.kpfu.itis.group11501.vitise.service.SemesterService;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final EntityManager entityManager;
    private final SemesterService semesterService;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, EntityManager entityManager, SemesterService semesterService) {
        this.groupRepository = groupRepository;
        this.entityManager = entityManager;
        this.semesterService = semesterService;
    }

    @Override
    public Group getGroup(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    public Map<Long, String> getGroupsByCourse(int course) {
        Map<Long, String> map = new HashMap<>();
        int k = course * 2;
        Semester semOne = semesterService.getBySemester(k);
        Semester semTwo = semesterService.getBySemester(k - 1);
        List<Group> groups = entityManager.createQuery
                ("select g from Group g where g.semester = :semOne or g.semester = :semTwo", Group.class)
                .setParameter("semOne", semOne).setParameter("semTwo", semTwo).getResultList();
        for (Group g : groups) {
            map.put(g.getId(), g.getName());
        }
        return map;
    }
}
