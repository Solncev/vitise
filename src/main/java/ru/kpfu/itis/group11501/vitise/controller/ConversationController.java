package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.vitise.model.Conversation;
import ru.kpfu.itis.group11501.vitise.model.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.ConversationMessage;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;
import ru.kpfu.itis.group11501.vitise.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 29.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Controller
public class ConversationController {

    private final UserService userService;
    private final ColleaguesService colleaguesService;
    private final ConversationService conversationService;

    @Autowired
    public ConversationController(UserService userService,
                                  ColleaguesService colleaguesService,
                                  ConversationService conversationService) {
        this.userService = userService;
        this.colleaguesService = colleaguesService;
        this.conversationService = conversationService;
    }
    @RequestMapping(value = "/messages/conversation/create", method = RequestMethod.POST)
    public String createConversation(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "members[]") Long[] membersId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> members = new ArrayList<>();
        for (Long id: membersId)
            members.add(userService.getUser(id));
        Conversation conversation = conversationService.createConversation(name, currentUser, members);
        return "redirect:/messages/conversation/" + conversation.getId();
    }

    @RequestMapping(value = "/messages/conversation/{id}")
    public String conversationPage(@PathVariable("id") Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Conversation conversation = conversationService.getConversation(id);
        if (conversationService.getMember(currentUser, conversation) == null)
            return "handle404";
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("conversation", conversation);
        model.addAttribute("message_form", new ConversationMessage());
        model.addAttribute("available_users", conversationService.getAvailableUsers(conversation));
        return "conversation";
    }

    @RequestMapping(value = "/messages/conversation/{id}/send", method = RequestMethod.POST)
    public String conversationSendMessage(@PathVariable("id") Long id,
                                          @ModelAttribute("message_form") ConversationMessage messageForm) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Conversation conversation = conversationService.getConversation(id);

        if (conversationService.getMember(currentUser, conversation) == null)
            return "handle404";
        ConversationMessage message = new ConversationMessage();
        message.setText(messageForm.getText());
        message.setUser(currentUser);
        message.setConversation(conversation);
        conversationService.addMessage(message);

        return "redirect:/messages/conversation/" + id;
    }

    @RequestMapping(value = "/messages/conversation/{id}/change", method = RequestMethod.POST)
    public String conversationChange(@PathVariable("id") Long id,
                                     @RequestParam(name = "name") String name) {
        Conversation conversation = conversationService.getConversation(id);
        conversation.setName(name);
        conversationService.saveConversation(conversation);

        return "redirect:/messages/conversation/" + id;
    }

    @RequestMapping(value = "/messages/conversation/{id}/add", method = RequestMethod.POST)
    public String conversationAddMember(@PathVariable("id") Long id,
                                        @RequestParam(name = "member_id") Long memberId) {
        ConversationMember member = new ConversationMember();
        member.setUser(userService.getUser(memberId));
        member.setConversation(conversationService.getConversation(id));
        conversationService.addMember(member);

        return "redirect:/messages/conversation/" + id;
    }

    @RequestMapping(value = "/messages/conversation/{id}/delete", method = RequestMethod.POST)
    public String conversationDeleteMember(@PathVariable("id") Long id,
                                           @RequestParam(name = "member_id") Long memberId) {

        ConversationMember member =
                conversationService.getMember(userService.getUser(memberId), conversationService.getConversation(id));
        conversationService.deleteMember(member);

        return "redirect:/messages/conversation/" + id;
    }
}