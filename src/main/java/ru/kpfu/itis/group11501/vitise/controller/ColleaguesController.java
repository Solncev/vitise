package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Наталья on 22.03.2017.
 */
@Controller
public class ColleaguesController {
    private final ColleaguesService colleaguesService;
    private final UserService userService;

    @Autowired
    public ColleaguesController(ColleaguesService colleaguesService, UserService userService) {
        this.colleaguesService = colleaguesService;
        this.userService = userService;
    }

    @RequestMapping(value = "/colleagues/{id}/add", method = RequestMethod.POST)
    public String addColleague(@PathVariable(name = "id") String idUser, HttpServletRequest request) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(Long.valueOf(idUser));
        colleaguesService.add(currentUser, user);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/colleagues/{id}/approve", method = RequestMethod.POST)
    public String approveColleague(@PathVariable(name = "id") String idColleague, HttpServletRequest request) {
        colleaguesService.approve(Long.valueOf(idColleague));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/colleagues/{id}/delete", method = RequestMethod.POST)
    public String deleteColleague(@PathVariable(name = "id") String idColleague, HttpServletRequest request) {
        colleaguesService.delete(Long.valueOf(idColleague));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping("/colleagues")
    public String pageColleagues(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myrequests", colleaguesService.getRequestSender(currentUser));
        model.addAttribute("requests", colleaguesService.getRequestReceiver(currentUser));
        model.addAttribute("colleagues", colleaguesService.getAllColleagues(currentUser));
        model.addAttribute("user", currentUser);
        return "colleagues";
    }
}
