package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.NewsFile;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.NewsFileService;
import ru.kpfu.itis.group11501.vitise.service.NewsService;
import ru.kpfu.itis.group11501.vitise.util.FileUploader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Created by Марат on 22.03.2017.
 */
@Controller
public class NewsController {
    private final Pattern pattern = Pattern.compile("^\\S+$");
    private final NewsService newsService;
    private final NewsFileService newsFileService;

    @Autowired
    public NewsController(NewsService newsService, NewsFileService newsFileService) {
        this.newsService = newsService;
        this.newsFileService = newsFileService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String news(@RequestParam Map<String, String> allRequestParams,
                       @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        News news = new News();
        news.setPubDate(new Date(System.currentTimeMillis()));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAuthor(user);
        news.setPersonal(true);
        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY")) {
            news.setPersonal(false);
        }
        news.setText(allRequestParams.get("text"));
        newsService.add(news);
        NewsFile newsFile = new NewsFile();
        newsFile.setNews(news);
        String name = FileUploader.uploadFile(file, "news");
        newsFile.setFilename(name);
        newsFileService.add(newsFile);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/news/remove", method = RequestMethod.POST)
    public String newsRemove(@RequestParam(name = "news_id") Long newsId, HttpServletRequest request) {
        if (newsFileService.findByNewsId(newsId).getFilename() != null) {
            File file = new File(FileUploader.getRootPath() + newsFileService.findByNewsId(newsId).getFilename());
            file.delete();
        }
        newsService.remove(newsService.get(newsId));
        return "redirect:" + request.getHeader("Referer");
    }
}
