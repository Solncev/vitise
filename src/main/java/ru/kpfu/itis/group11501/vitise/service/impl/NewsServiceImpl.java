package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.repository.NewsRepository;
import ru.kpfu.itis.group11501.vitise.service.NewsService;

import java.util.List;

/**
 * Created by Марат on 22.03.2017.
 */
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public void add(News news) {
        newsRepository.save(news);
    }

    @Override
    public void remove(News news) {
        newsRepository.delete(news);
    }

    @Override
    public News get(Long id) {
        return newsRepository.getOne(id);
    }

    @Override
    public List<News> getAllByAuthor(User author) {
        return newsRepository.getAllByAuthorId(author.getId());
    }

    @Override
    public List<News> getAllPublicNews() {
        return newsRepository.getAllByIsPersonal(false);
    }
}
