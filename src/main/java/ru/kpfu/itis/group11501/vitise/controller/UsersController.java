package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.model.Colleagues;
import ru.kpfu.itis.group11501.vitise.model.DirectionOfScientificActivity;
import ru.kpfu.itis.group11501.vitise.model.News;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.*;

import java.io.File;
import java.util.*;

/**
 * Created by Наталья on 22.03.2017.
 */
@Controller
public class UsersController {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserService userService;
    private final ColleaguesService colleaguesService;
    private final DirectionOfScientificActivityService directionOfScientificActivityService;
    private final DirectionOfScientificActivityUserService directionOfScientificActivityUserService;
    private final NewsService newsService;

    @Autowired
    public UsersController(UserService userService, ColleaguesService colleaguesService, DirectionOfScientificActivityService directionOfScientificActivityService, DirectionOfScientificActivityUserService directionOfScientificActivityUserService, NewsService newsService) {
        this.userService = userService;
        this.colleaguesService = colleaguesService;
        this.directionOfScientificActivityService = directionOfScientificActivityService;
        this.directionOfScientificActivityUserService = directionOfScientificActivityUserService;
        this.newsService = newsService;
    }

    @RequestMapping("/user/{id}")
    public String userPage(@PathVariable(name = "id") String id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(Long.valueOf(id));
        if (Objects.equals(currentUser.getId(), user.getId())) {
            return "redirect:/profile";
        } else if (!Objects.equals(currentUser.getId(), user.getId())) {
            Colleagues c = colleaguesService.getColleagues(currentUser, user);
            model.addAttribute("state", colleaguesService.getState(c, currentUser, user));
            model.addAttribute("colleagues", c);
        }
        model.addAttribute("userPage", user);
        model.addAttribute("user", currentUser);
        model.addAttribute("newses", newsService.getAllByAuthor(user));
        return "user_page";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfilePage(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Colleagues c = colleaguesService.getColleagues(currentUser, currentUser);
        model.addAttribute("state", colleaguesService.getState(c, currentUser, currentUser));
        model.addAttribute("colleagues", c);
        model.addAttribute("userPage", currentUser);
        model.addAttribute("user", currentUser);
        model.addAttribute("news", new News());
        model.addAttribute("newses", newsService.getAllByAuthor(currentUser));
        return "user_page";
    }
    
    @RequestMapping(value = "/profile/change", method = RequestMethod.GET)
    public String getChangeProfile(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", currentUser);
        model.addAttribute("isWorker", userService.isWorker(currentUser));
        Map<String, String> directions = new HashMap<>();
        List<DirectionOfScientificActivity> directionList = directionOfScientificActivityService.findAll();
        List<DirectionOfScientificActivity> userDirectionList = directionOfScientificActivityService.findByUser(currentUser);
        for (DirectionOfScientificActivity d : directionList) {
            if (isContain(userDirectionList, d)) {
                directions.put(d.getName(), "checked");
            } else {
                directions.put(d.getName(), "");
            }
        }
        System.out.println(directions);
        model.addAttribute("directions", directions);
        return "profile_change";
    }

    @RequestMapping(value = "/profile/change", method = RequestMethod.POST)
    public Object ChangeProfile(Model model,
                                @RequestParam("photo") File file,
                                @RequestParam Map<String, String> allRequestParams) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!allRequestParams.get("password").equals("")) {
            if (encoder.matches(allRequestParams.get("password"), currentUser.getPassword())) {
                currentUser.setPassword(encoder.encode(allRequestParams.get("newPassword")));
            }
        }
        if (file != null)
            currentUser.setPhotoName(file.getAbsolutePath());
        currentUser.setDescription(allRequestParams.get("description"));
        List<String> directions = new ArrayList<>();
        List<DirectionOfScientificActivity> directionOfScientificActivities = directionOfScientificActivityService.findAll();
        for (DirectionOfScientificActivity d : directionOfScientificActivities) {
            if (allRequestParams.get(d.getName()) != null) {
                directions.add(allRequestParams.get(d.getName()));
            }
        }
        directionOfScientificActivityUserService.add(currentUser, directions);
        userService.add(currentUser);
        return "redirect:/profile/change";
    }

    private boolean isContain(List<DirectionOfScientificActivity> directionOfScientificActivities, DirectionOfScientificActivity d) {
        for (DirectionOfScientificActivity direction : directionOfScientificActivities) {
            if (direction.getName().equals(d.getName())) {
                return true;
            }
        }
        return false;
    }
}
