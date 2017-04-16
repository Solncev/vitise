package ru.kpfu.itis.group11501.vitise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.group11501.vitise.model.NewsFile;

/**
 * Created by Марат on 05.04.2017.
 */
@Component
public interface NewsFileRepository extends JpaRepository<NewsFile, Long> {
    NewsFile findByFilename(String filename);

    NewsFile findByNewsId(Long newsId);
}
