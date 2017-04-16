package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.vitise.model.News;

import java.util.List;

/**
 * Created by Марат on 22.03.2017.
 */
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> getAllByAuthorId(Long id);

    List<News> getAllByIsPersonal(boolean isPersonal);
}
