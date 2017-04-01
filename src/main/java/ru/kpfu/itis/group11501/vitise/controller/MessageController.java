package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.vitise.model.*;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;
import ru.kpfu.itis.group11501.vitise.service.MessageService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Марат on 25.03.2017.
 */
@Controller
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    private final ColleaguesService colleaguesService;
    private final ConversationService conversationService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService,
                             ColleaguesService colleaguesService, ConversationService conversationService) {
        this.messageService = messageService;
        this.userService = userService;
        this.colleaguesService = colleaguesService;
        this.conversationService = conversationService;
    }

    @RequestMapping(value = "/messages/user/{id}")
    public String getChat(@PathVariable("id") Long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("messages", messageService.findAll(id, user.getId()));
        model.addAttribute("messageForm", new Message());
        model.addAttribute("interlocutor", id);
        return "chat_page";
    }

    @RequestMapping(value = "/messages/user/{id}/send", method = RequestMethod.POST)
    public String post(@ModelAttribute("messageForm") Message messageForm, @PathVariable("id") Long id, Model model) {
        Message message = new Message();
        message.setMessage(messageForm.getMessage());
        message.setDate(new Date(System.currentTimeMillis()));
        message.setSender((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        message.setRecipient(userService.findOne(Long.valueOf(id)));
        messageService.add(message);
        return "redirect:/messages/user/" + id;
    }

    @RequestMapping(value = "/messages")
    public String getAllChats(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Long> interlocutorsId = messageService.findAllInterlocutors(user);
        List<User> interlocutors = new ArrayList<>();
        for (Long id : interlocutorsId) {
            interlocutors.add(userService.findOne(id));
        }
        model.addAttribute("colleagues", colleaguesService.getColleagueUsers(user));
        model.addAttribute("interlocutors", interlocutors);
        model.addAttribute("conversations", conversationService.getConversations(user));
        return "messages";
    }

}
