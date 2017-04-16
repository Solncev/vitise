package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;
import ru.kpfu.itis.group11501.vitise.service.CommunicationRelationService;
import ru.kpfu.itis.group11501.vitise.service.UserMessageService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.regex.Pattern;

/**
 * Created by Марат on 08.04.2017.
 */
@Controller
public class TechnicalSupportController {
    private final UserService userService;
    private final CommunicationRelationService communicationRelationsService;
    private final UserMessageService usersMessagesService;
    private final Pattern pattern = Pattern.compile("^\\S+$");

    @Autowired
    public TechnicalSupportController(UserService userService, CommunicationRelationService communicationRelationsService, UserMessageService usersMessagesService) {
        this.userService = userService;
        this.communicationRelationsService = communicationRelationsService;
        this.usersMessagesService = usersMessagesService;
    }

    @RequestMapping(value = "/support", method = RequestMethod.GET)
    public String getSupport() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User admin = userService.getAdmin();
        if (admin != null) {
            CommunicationRelation communicationRelations = communicationRelationsService.getOne(user, admin);
            if (communicationRelations != null) {
                return "redirect:/messages/dialog/" + communicationRelations.getId();
            } else {
                return "support";
            }
        }
        return "handle404";
    }

    @RequestMapping(value = "/support", method = RequestMethod.POST)
    public String sendMessage(@RequestParam(name = "message") String message) {
        User admin = userService.getAdmin();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        communicationRelationsService.createCommunicationRelations(user, admin);
        UserMessage usersMessages = new UserMessage();
        usersMessages.setMessage(message);
        CommunicationRelation communicationRelations = communicationRelationsService.getOne(user, admin);
        usersMessages.setCommunicationRelation(communicationRelations);
        usersMessages.setUser(user);
        if (pattern.matcher(usersMessages.getMessage()).matches()) {
            usersMessagesService.addUsersMessages(usersMessages);
        }
        return "redirect:/messages/dialog/" + communicationRelations.getId();
    }
}
