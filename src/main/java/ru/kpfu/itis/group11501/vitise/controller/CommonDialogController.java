package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.CommonDialogService;

/**
 * Author: Svintenok Kate
 * Date: 06.04.2017
 * Group: 11-501
 * Project: vITISe
 */
@Controller
@RequestMapping("/messages")
public class CommonDialogController {

    private final ColleaguesService colleaguesService;
    private final CommonDialogService commonDialogService;

    @Autowired
    public CommonDialogController(ColleaguesService colleaguesService,
                                  CommonDialogService commonDialogService) {
        this.colleaguesService = colleaguesService;
        this.commonDialogService = commonDialogService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllDialogs(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("colleagues", colleaguesService.getColleagueUsers(currentUser));
        model.addAttribute("dialogs", commonDialogService.getDialogs(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "messages";
    }

    @RequestMapping(value = "/get_number")
    @ResponseBody
    public Integer getMessagesNumber(@RequestParam(name = "messages", required = false) int messages) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Integer(commonDialogService.newMessagesCount(currentUser));
    }
}