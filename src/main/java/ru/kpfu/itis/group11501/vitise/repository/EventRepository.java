package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.Event;

import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByIsPublicOrderByPubDateDesc(boolean isPublic);

    List<Event> findByAuthorIdOrderByPubDateDesc(Long id);
}
