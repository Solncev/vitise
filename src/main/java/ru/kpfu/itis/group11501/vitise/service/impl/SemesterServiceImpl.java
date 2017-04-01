package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.Semester;
import ru.kpfu.itis.group11501.vitise.repository.SemesterRepository;
import ru.kpfu.itis.group11501.vitise.service.SemesterService;

/**
 * Created by Наталья on 25.03.2017.
 */
@Service
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Semester getBySemester(int semester) {
        return semesterRepository.findOneBySemester(semester);
    }
}
