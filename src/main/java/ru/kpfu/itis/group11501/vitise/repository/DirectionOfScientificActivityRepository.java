package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivity;

/**
 * Created by Марат on 28.03.2017.
 */
public interface DirectionOfScientificActivityRepository extends JpaRepository<DirectionOfScientificActivity, Long> {
    DirectionOfScientificActivity getByName(String direction);
}
