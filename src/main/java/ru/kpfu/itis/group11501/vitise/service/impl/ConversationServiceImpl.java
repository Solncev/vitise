package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.*;
import ru.kpfu.itis.group11501.vitise.repository.ConversationMessageRepository;
import ru.kpfu.itis.group11501.vitise.repository.ConversationRepository;
import ru.kpfu.itis.group11501.vitise.repository.ConversationMemberRepository;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMemberRepository conversationMemberRepository;
    private final ConversationMessageRepository conversationMessageRepository;
    private final ColleaguesService colleaguesService;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository,
                                   ConversationMemberRepository conversationMemberRepository,
                                   ConversationMessageRepository conversationMessageRepository,
                                   ColleaguesService colleaguesService) {
        this.conversationRepository = conversationRepository;
        this.conversationMemberRepository = conversationMemberRepository;
        this.conversationMessageRepository = conversationMessageRepository;
        this.colleaguesService = colleaguesService;
    }

    @Override
    public void saveConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    @Override
    public Conversation createConversation(String name, User creator, List<User> members) {
        Conversation conversation = new Conversation();
        conversation.setName(name);
        conversation.setCreator(creator);
        conversation = conversationRepository.save(conversation);
        members.add(creator);
        for (User member: members)
            this.addMember(conversation, member);
        return conversation;
    }

    @Override
    public void addMember(Conversation conversation, User user) {
        ConversationMember conversationMember = new ConversationMember();
        conversationMember.setUser(user);
        conversationMember.setConversation(conversation);
        conversationMemberRepository.save(conversationMember);
    }

    @Override
    public void addMessage(ConversationMessage conversationMessage) {
        conversationMessageRepository.save(conversationMessage);
    }

    @Override
    public Conversation getConversation(Long id) {
        Conversation conversation = conversationRepository.findOne(id);
        conversation.setMessages(this.getMessages(conversation));
        conversation.setMembers(this.getMembers(conversation));
        return conversation;
    }


    @Override
    public List<Conversation> getConversations(User user) {
        List<ConversationMember> members = conversationMemberRepository.findByUserIdAndIsActive(user.getId(), true);
        List<Conversation> conversations = new ArrayList<>();
        for (ConversationMember member: members)
            conversations.add(member.getConversation());
        return conversations;
    }

    @Override
    public ConversationMember getMember(User user, Conversation conversation) {
        return conversationMemberRepository.findOneByUserIdAndConversationId(user.getId(), conversation.getId());
    }

    @Override
    public void deleteMember(ConversationMember member) {
        conversationMemberRepository.delete(member);
    }

    @Override
    public void addMember(ConversationMember member) {
        conversationMemberRepository.save(member);
    }

    @Override
    public List<User> getAvailableUsers(Conversation conversation) {
        List<User> availableUsers = new ArrayList<>();
        for (User user: colleaguesService.getColleagueUsers(conversation.getCreator()))
            if (this.getMember(user, conversation) == null)
                availableUsers.add(user);
        return availableUsers;
    }

    private List<ConversationMessage> getMessages(Conversation conversation) {
        return conversationMessageRepository.findAllByConversationIdOrderByDateDesc(conversation.getId());
    }


    private List<User> getMembers(Conversation conversation) {
        List<User> members = new ArrayList<>();
        for (ConversationMember member:
                conversationMemberRepository.findByConversationIdAndIsActive(conversation.getId(), true))
            members.add(member.getUser());

        return members;
    }

}