package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.UsersStatus;

import java.util.List;

public interface UsersStatusRepository extends JpaRepository<UsersStatus, Long> {
    List<UsersStatus> findAllStatusIdByUserId(Long id);

    List<UsersStatus> findAllByStatusId(Long id);
}
