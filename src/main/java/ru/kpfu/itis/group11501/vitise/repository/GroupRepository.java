package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findOneByName(String name);
}
