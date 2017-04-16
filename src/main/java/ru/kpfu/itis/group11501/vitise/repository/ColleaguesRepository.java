package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.Colleagues;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;

/**
 * Created by Наталья on 22.03.2017.
 */
public interface ColleaguesRepository extends JpaRepository<Colleagues, Long> {

    @Query("select c from Colleagues c where c.sender = :s and c.receiver = :r or c.sender = :r and c.receiver = :s")
    Colleagues getColleagues(@Param("s") User userOne, @Param("r") User userTwo);

    @Query("select c from Colleagues c where c.sender = :sender and c.isActive = false")
    List<Colleagues> getRequestSender(@Param("sender") User sender);

    @Query("select c from Colleagues c where c.receiver = :receiver and c.isActive = false")
    List<Colleagues> getRequestReceiver(@Param("receiver") User receiver);

    @Query("select c from Colleagues c where (c.receiver = :user or c.sender = :user) and c.isActive = true")
    List<Colleagues> getAllColleagues(@Param("user") User user);

    @Query("select count(c) from Colleagues c where c.receiver = :user and c.isActive = false")
    int getRequestsCount(@Param("user") User user);
}
