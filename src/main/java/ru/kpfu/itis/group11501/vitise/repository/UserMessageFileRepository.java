package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.UserMessageFile;

/**
 * Created by Марат on 10.04.2017.
 */
public interface UserMessageFileRepository extends JpaRepository<UserMessageFile, Long> {
    UserMessageFile findByUserMessageId(Long userMessageId);
}
