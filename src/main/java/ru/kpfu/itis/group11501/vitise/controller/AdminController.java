package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.enums.StatusName;
import ru.kpfu.itis.group11501.vitise.service.AdminService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan Popov on 05.04.2017.
 */
@Controller
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/admin/users")
    public String getUsersList(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = userService.getConfirmedUsers();
        users.remove(currentUser);
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("statusNames", adminService.getPermissionsForArchive());
        return "admin_users_page";
    }

    @RequestMapping(value = "/admin/users/archive", method = RequestMethod.POST)
    public String archiveUser(@RequestParam(name = "id") String idUser, HttpServletRequest request) {
        userService.changeState(Long.valueOf(idUser));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/admin/users/filter")
    public String filterUserList(Model model, @RequestParam(name = "isActive") String isActive, @RequestParam(name = "status") String status, @RequestParam(name = "searchField", required = false) String searchField) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("current_user", currentUser);
        List<StatusName> statusNameList = new ArrayList<>();
        if (status.equals("all")) {
            statusNameList = adminService.getPermissionsForArchive();
        } else {
            if (adminService.checkPermissionsForArchive(Long.valueOf(status))) {
                statusNameList.add(StatusName.values()[Integer.valueOf(status)]);
            } else {
                return "handle404";
            }
        }
        List<User> users;
        String[] args = searchField.split(" ");
        if (!isActive.equals("null")) {
            users = userService.filterUserListByCriteria(Boolean.valueOf(isActive), statusNameList, args);
        } else {
            users = userService.filterUserListByCriteria(null, statusNameList, args);
        }
        users.remove(currentUser);
        model.addAttribute("statusNames", adminService.getPermissionsForArchive());
        model.addAttribute("users", users);
        return "admin_users_page";
    }
}
