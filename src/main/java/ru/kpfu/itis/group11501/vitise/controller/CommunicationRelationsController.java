package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.vitise.model.CommunicationRelation;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.UserMessage;
import ru.kpfu.itis.group11501.vitise.model.UserMessageFile;
import ru.kpfu.itis.group11501.vitise.service.*;
import ru.kpfu.itis.group11501.vitise.util.FileUploader;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by Наталья on 06.04.2017.
 */
@Controller
@RequestMapping("/messages")
public class CommunicationRelationsController {
    private final Pattern pattern = Pattern.compile("^\\S+$");
    private final CommunicationRelationService communicationRelationsService;
    private final UserMessageService userMessageService;
    private final ConversationService conversationService;
    private final ColleaguesService colleaguesService;
    private final UserService userService;
    private final UserMessageFileService userMessageFileService;

    @Autowired
    public CommunicationRelationsController(CommunicationRelationService communicationRelationsService,
                                            UserMessageService userMessageService,
                                            ConversationService conversationService,
                                            ColleaguesService colleaguesService,
                                            UserService userService, UserMessageFileService userMessageFileService) {
        this.communicationRelationsService = communicationRelationsService;
        this.userMessageService = userMessageService;
        this.conversationService = conversationService;
        this.colleaguesService = colleaguesService;
        this.userService = userService;
        this.userMessageFileService = userMessageFileService;
    }

    @RequestMapping(value = "/dialog/{id}", method = RequestMethod.GET)
    public String getDialogPage(@PathVariable(name = "id") Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CommunicationRelation communicationRelation = communicationRelationsService.getOne(id);
        if (communicationRelation == null || !accessToChat(currentUser, communicationRelation))
            return "handle404";
        communicationRelation = communicationRelationsService.getOneWithInfo(id, currentUser);
        model.addAttribute("relation", communicationRelation);
        model.addAttribute("currentUser", currentUser);

        communicationRelationsService.updateReadingLog(communicationRelation, currentUser);
        return "chat_page";
    }

    @RequestMapping(value = "/dialog/{id}/send", method = RequestMethod.POST)
    public String sendMessage(@RequestParam(name = "message") String message,
                              @PathVariable(name = "id") Long id,
                              @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        UserMessage usersMessages = new UserMessage();
        usersMessages.setMessage(message);
        usersMessages.setCommunicationRelation(communicationRelationsService.getOne(id));
        usersMessages.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        userMessageService.addUsersMessages(usersMessages);
        UserMessageFile userMessageFile = new UserMessageFile();
        userMessageFile.setUserMessage(usersMessages);
        String name = FileUploader.uploadFile(file, "userMessageFiles");
        userMessageFile.setFilename(name);
        userMessageFileService.add(userMessageFile);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/dialog/create/{id}", method = RequestMethod.POST)
    public String createDialog(@PathVariable(name = "id") Long userId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userId);
        CommunicationRelation communicationRelation = communicationRelationsService.getOne(currentUser, user);
        if (communicationRelation != null) {
            return "redirect:/messages/dialog/" + communicationRelation.getId();
        }
        communicationRelationsService.createCommunicationRelations(user, currentUser);
        communicationRelation = communicationRelationsService.getOne(user, currentUser);
        return "redirect:/messages/dialog/" + communicationRelation.getId();
    }

    private boolean accessToChat(User user, CommunicationRelation communicationRelation) {
        return communicationRelation.getFirst().equals(user) || communicationRelation.getSecond().equals(user);
    }
}
