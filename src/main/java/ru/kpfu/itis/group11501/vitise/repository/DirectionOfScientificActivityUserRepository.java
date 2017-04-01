package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivityUser;

import java.util.List;

/**
 * Created by Марат on 28.03.2017.
 */
public interface DirectionOfScientificActivityUserRepository extends JpaRepository<DirectionOfScientificActivityUser, Long> {
    List<DirectionOfScientificActivityUser> findDirectionOfScientificActivityByUserId(Long userId);
}
