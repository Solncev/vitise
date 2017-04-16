package ru.kpfu.itis.group11501.vitise.service;

import ru.kpfu.itis.group11501.vitise.model.Group;
import ru.kpfu.itis.group11501.vitise.model.Student;
import ru.kpfu.itis.group11501.vitise.model.User;

public interface StudentService {

    void add(Student student);

    Group getStudentGroup(User user);
}
