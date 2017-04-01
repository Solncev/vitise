package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findOneByName(String statusName);
}
