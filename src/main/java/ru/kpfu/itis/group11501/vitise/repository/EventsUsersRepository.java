package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.EventsUsers;

/**
 * Author: Svintenok Kate
 * Date: 24.03.2017
 * Group: 11-501
 * Project: vITISe
 */
public interface EventsUsersRepository extends JpaRepository<EventsUsers, Long> {
    EventsUsers findOneByEventIdAndUserId(Long eventId, Long userId);

    Long countByEventId(Long id);
}
