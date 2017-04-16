package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.service.StatusService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

/**
 * Created by Наталья on 26.03.2017.
 */
@Controller
@RequestMapping("/sign_up_request")
public class SignUpRequestsController {
    private final UserService userService;
    private final StatusService statusService;

    @Autowired
    public SignUpRequestsController(UserService userService, StatusService statusService) {
        this.userService = userService;
        this.statusService = statusService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllRequests(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("students", userService.getNewUsers(statusService.getStatus(StatusName.STUDENT)));
        model.addAttribute("NPRs", userService.getNewUsers(statusService.getStatus(StatusName.WORKER)));
        return "sign_up_request";
    }

    @RequestMapping(value = "/{id}/approve", method = RequestMethod.POST)
    public String approveSignUp(@PathVariable(name = "id") String userId) {
        userService.approveUser(Long.parseLong(userId));
        return "redirect:/sign_up_request";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteRequest(@PathVariable(name = "id") String userId) {
        userService.deleteUser(Long.parseLong(userId));
        return "redirect:/sign_up_request";
    }
}
