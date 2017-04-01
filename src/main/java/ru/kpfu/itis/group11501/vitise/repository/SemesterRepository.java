package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Semester;

/**
 * Created by Наталья on 25.03.2017.
 */
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findOneBySemester(int semester);
}
