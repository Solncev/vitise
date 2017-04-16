package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.vitise.model.Status;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findOneByEmail(String email);

    @Query("select user from User user WHERE user.isActive is null and " +
            "exists (select us from UsersStatus us where us.status = :status and us.user = user)")
    List<User> findNewByStatus(@Param("status") Status status);

    List<User> findAllByIsActive(Boolean isActive);

    List<User> findAllByIsActiveNotNullOrderBySurname();
}
