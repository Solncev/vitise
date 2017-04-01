package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * Created by Марат on 22.03.2017.
 */
@Controller
public class NewsController {
    private final Pattern pattern = Pattern.compile("^\\S+$");
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public String news(@ModelAttribute(name = "news") News news, HttpServletRequest request) {
        news.setDate(new Date(System.currentTimeMillis()));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAuthor(user);
        news.setPersonal(false);
        if (request.isUserInRole("ROLE_WORKER") || request.isUserInRole("ROLE_DEANERY")) {
            news.setPersonal(true);
        }
        if (pattern.matcher(news.getText()).matches()) {
            newsService.add(news);
        }
        return "redirect:/profile";
    }

    @RequestMapping(value = "/news/remove", method = RequestMethod.POST)
    public String newsRemove(@RequestParam(name = "news_id") Long newsId, HttpServletRequest request) {
        newsService.remove(newsService.get(newsId));
        return "redirect:" + request.getHeader("Referer");
    }

}
