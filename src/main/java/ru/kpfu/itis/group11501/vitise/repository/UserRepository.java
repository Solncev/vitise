package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    User findOneById(Long id);

    List<User> findAllByIsActive(Boolean isActive);
}
