package ru.kpfu.itis.group11501.vitise.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Student;
import ru.kpfu.itis.group11501.vitise.model.User;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findOneByStudent(User user);
}
