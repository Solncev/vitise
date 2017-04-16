package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.NewsFile;
import ru.kpfu.itis.group11501.vitise.repository.NewsFileRepository;
import ru.kpfu.itis.group11501.vitise.service.NewsFileService;

/**
 * Created by Марат on 05.04.2017.
 */
@Service
public class NewsFileServiceImpl implements NewsFileService {
    @Autowired
    private final NewsFileRepository newsFileRepository;

    public NewsFileServiceImpl(NewsFileRepository newsFileRepository) {
        this.newsFileRepository = newsFileRepository;
    }

    @Override
    public NewsFile findOne(Long id) {
        return newsFileRepository.findOne(id);
    }

    @Override
    public NewsFile findByFileName(String filename) {
        return newsFileRepository.findByFilename(filename);
    }

    @Override
    public NewsFile findByNewsId(Long newsId) {
        return newsFileRepository.findByNewsId(newsId);
    }

    @Override
    public void add(NewsFile newsFile) {
        newsFileRepository.save(newsFile);
    }

    @Override
    public void remove(NewsFile newsFile) {
        newsFileRepository.delete(newsFile);
    }
}
