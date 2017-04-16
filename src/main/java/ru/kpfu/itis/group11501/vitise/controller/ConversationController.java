package ru.kpfu.itis.group11501.vitise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.Conversation;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMember;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessage;
import ru.kpfu.itis.group11501.vitise.model.conversation.ConversationMessageFile;
import ru.kpfu.itis.group11501.vitise.model.conversation.enums.ActiveStatusName;
import ru.kpfu.itis.group11501.vitise.service.ConversationMessageFileService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;
import ru.kpfu.itis.group11501.vitise.service.UserService;
import ru.kpfu.itis.group11501.vitise.util.FileUploader;

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
    private final ConversationService conversationService;
    private final ConversationMessageFileService conversationMessageFileService;

    @Autowired
    public ConversationController(UserService userService,
                                  ConversationService conversationService, ConversationMessageFileService conversationMessageFileService) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.conversationMessageFileService = conversationMessageFileService;
    }

    @RequestMapping(value = "/messages/conversation/create", method = RequestMethod.POST)
    public String createConversation(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "members") Long[] membersId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> members = new ArrayList<>();
        for (Long id : membersId)
            members.add(userService.getUser(id));
        Conversation conversation = conversationService.createConversation(name, currentUser, members);
        return "redirect:/messages/conversation/" + conversation.getId();
    }

    @RequestMapping(value = "/messages/conversation/{id}")
    public String conversationPage(@PathVariable("id") Long id, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (conversationService.getMember(currentUser, conversationService.getConversation(id)) == null)
            return "handle404";
        Conversation conversation = conversationService.getConversationWithInfo(id, currentUser);
        ConversationMember member = conversationService.getMember(currentUser, conversation);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("conversation", conversation);
        model.addAttribute("message_form", new ConversationMessage());
        model.addAttribute("available_users", conversationService.getAvailableUsers(conversation));

        conversationService.updateReadingLog(member);
        return "conversation";
    }

    @RequestMapping(value = "/messages/conversation/{id}/send", method = RequestMethod.POST)
    public String conversationSendMessage(@PathVariable("id") Long id,
                                          @RequestParam("file") MultipartFile file,
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
        ConversationMessageFile conversationMessageFile = new ConversationMessageFile();
        conversationMessageFile.setConversationMessage(message);
        String name = FileUploader.uploadFile(file, "conversationMessageFiles");
        conversationMessageFile.setFilename(name);
        conversationMessageFileService.add(conversationMessageFile);
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
                                        @RequestParam(name = "user_id") Long userId) {
        conversationService.addMember(conversationService.getConversation(id), userService.getUser(userId));
        return "redirect:/messages/conversation/" + id;
    }

    @RequestMapping(value = "/messages/conversation/{id}/delete", method = RequestMethod.POST)
    public String conversationDeleteMember(@PathVariable("id") Long id,
                                           @RequestParam(name = "user_id") Long userId) {

        ConversationMember member =
                conversationService.getMember(userService.getUser(userId), conversationService.getConversation(id));
        conversationService.memberChangeStatus(member, ActiveStatusName.DELETED);
        return "redirect:/messages/conversation/" + id;
    }

    @RequestMapping(value = "/messages/conversation/{id}/change_status", method = RequestMethod.POST)
    public String conversationChangeStatus(@PathVariable("id") Long id,
                                           @RequestParam(name = "user_id") Long userId) {
        ConversationMember member =
                conversationService.getMember(userService.getUser(userId), conversationService.getConversation(id));
        if (member.getActiveStatusName() == ActiveStatusName.ACTIVE)
            conversationService.memberChangeStatus(member, ActiveStatusName.LEFT_OUT);
        else
            conversationService.memberChangeStatus(member, ActiveStatusName.ACTIVE);
        return "redirect:/messages/conversation/" + id;
    }
}