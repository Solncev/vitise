package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.CommonNewsService;
import ru.kpfu.itis.group11501.vitise.service.EventService;
import ru.kpfu.itis.group11501.vitise.service.NewsService;

import java.util.List;

/**
 * Created by Наталья on 05.04.2017.
 */
@Controller
public class CommonNewsController {
    private final CommonNewsService commonNewsService;
    private final NewsService newsService;
    private final EventService eventService;

    @Autowired
    public CommonNewsController(CommonNewsService commonNewsService, NewsService newsService, EventService eventService) {
        this.commonNewsService = commonNewsService;
        this.newsService = newsService;
        this.eventService = eventService;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String commonNews(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("common_news", commonNewsService.getAllCommonNews(currentUser));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("news", new News());
        return "news";
    }


    //ajax
    @RequestMapping(value = "/get_news")
    @ResponseBody
    public List getNews(@RequestParam(name = "filter", required = false) String filter) {
        if (filter.equals("events")) {
            return eventService.getEvents((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else if (filter.equals("common")) {
            return commonNewsService.getAllCommonNews((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        } else if (filter.equals("all")) {
            return newsService.getAllPublicNews();
        }
        return newsService.getAllPublicNews();
    }

//
//    @RequestMapping(value = "/get_news", method = RequestMethod.GET)
//    @ResponseBody
//    public List<News> getNews() {
//        return newsService.getAllPublicNews();
//    }
//
//    @RequestMapping(value = "/get_events", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Event> getEvents() {
//        return eventService.getEvents((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    }
//
//    @RequestMapping(value = "/get_common_news", method = RequestMethod.GET)
//    @ResponseBody
//    public List<CommonNews> getAll() {
//        return commonNewsService.getAllCommonNews((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    }
}
