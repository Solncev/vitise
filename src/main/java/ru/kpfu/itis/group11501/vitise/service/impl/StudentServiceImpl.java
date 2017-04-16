package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Group;
import ru.kpfu.itis.group11501.vitise.model.Student;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.StudentRepository;
import ru.kpfu.itis.group11501.vitise.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Group getStudentGroup(User user) {
        Student student = studentRepository.findOneByStudent(user);
        if (student != null) {
            return student.getGroup();
        }
        return null;
    }
}
