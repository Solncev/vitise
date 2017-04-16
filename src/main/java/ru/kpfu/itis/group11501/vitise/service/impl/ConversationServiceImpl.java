package ru.kpfu.itis.group11501.vitise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.*;
import ru.kpfu.itis.group11501.vitise.model.conversation.enums.ActiveStatusName;
import ru.kpfu.itis.group11501.vitise.repository.*;
import ru.kpfu.itis.group11501.vitise.service.ColleaguesService;
import ru.kpfu.itis.group11501.vitise.service.ConversationService;

import java.util.ArrayList;
import java.util.Date;
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
    private final UserStateChangingLogRepository stateChangingLogRepository;
    private final MessagesReadingLogRepository readingLogRepository;


    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository,
                                   ConversationMemberRepository conversationMemberRepository,
                                   ConversationMessageRepository conversationMessageRepository,
                                   ColleaguesService colleaguesService,
                                   UserStateChangingLogRepository stateChangingLogRepository,
                                   MessagesReadingLogRepository readingLogRepository) {
        this.conversationRepository = conversationRepository;
        this.conversationMemberRepository = conversationMemberRepository;
        this.conversationMessageRepository = conversationMessageRepository;
        this.colleaguesService = colleaguesService;
        this.stateChangingLogRepository = stateChangingLogRepository;
        this.readingLogRepository = readingLogRepository;
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
        for (User member : members) {
            this.addMember(conversation, member);
        }
        return conversation;
    }

    @Override
    public void addMember(Conversation conversation, User user) {
        ConversationMember conversationMember = conversationMemberRepository.
                findOneByUserIdAndConversationId(user.getId(), conversation.getId());
        if (conversationMember == null) {
            conversationMember = new ConversationMember();
            conversationMember.setUser(user);
            conversationMember.setConversation(conversation);
            conversationMemberRepository.save(conversationMember);
            this.updateReadingLog(conversationMember);
        } else
            this.memberChangeStatus(conversationMember, ActiveStatusName.ACTIVE);
    }

    @Override
    public void addMessage(ConversationMessage conversationMessage) {
        conversationMessageRepository.save(conversationMessage);
    }

    @Override
    public Conversation getConversation(Long id) {
        return conversationRepository.findOne(id);
    }

    @Override
    public Conversation getConversationWithInfo(Long id, User user) {
        Conversation conversation = this.getConversation(id);
        conversation.setMessages(this.getMessages(conversation, user));
        conversation.setMembers(this.getMembers(conversation));
        conversation.setCurrentMember(this.getMember(user, conversation));
        return conversation;
    }


    @Override
    public List<Conversation> getConversations(User user) {
        List<ConversationMember> members = conversationMemberRepository.findByUserId(user.getId());
        List<Conversation> conversations = new ArrayList<>();

        for (ConversationMember member : members) {
            member.setMessagesReadingLog(this.getReadingLog(member));
            Conversation conversation = member.getConversation();

            List<ConversationMessage> messages = this.getMessages(conversation, user);
            if (messages.size() > 0)
                conversation.setLastMessage(this.getMessages(conversation, user).get(0));

            List<Date> stateChangingDates = stateChangingLogRepository.findAllDateByMember(member);
            conversation.setNewMessagesCount(conversationMessageRepository.newMessagesCount(
                    conversation, stateChangingDates, member.getMessagesReadingLog().getDate()));
            conversation.setCurrentMember(member);
            conversations.add(conversation);
        }
        return conversations;
    }

    @Override
    public ConversationMember getMember(User user, Conversation conversation) {
        ConversationMember member = conversationMemberRepository.
                findOneByUserIdAndConversationId(user.getId(), conversation.getId());
        if (member != null) {
            member.setMessagesReadingLog(this.getReadingLog(member));
        }
        return member;
    }

    @Override
    public void memberChangeStatus(ConversationMember member, ActiveStatusName status) {
        member.setActiveStatus(status);
        conversationMemberRepository.save(member);
        this.addStateChangingLog(member);
    }

    @Override
    public List<User> getAvailableUsers(Conversation conversation) {
        List<User> availableUsers = new ArrayList<>();
        for (User user : colleaguesService.getColleagueUsers(conversation.getCreator()))
            if (this.getMember(user, conversation) == null ||
                    this.getMember(user, conversation).getActiveStatusName() == ActiveStatusName.DELETED)
                availableUsers.add(user);
        return availableUsers;
    }


    @Override
    public void updateReadingLog(ConversationMember member) {
        MessagesReadingLog readingLog = this.getReadingLog(member);
        if (readingLog == null) {
            readingLog = new MessagesReadingLog();
            readingLog.setMember(member);
        } else
            readingLog.setDate(new Date(System.currentTimeMillis()));
        readingLogRepository.save(readingLog);
    }

    @Override
    public int newMessagesCount(User user) {
        List<Conversation> conversations = this.getConversations(user);
        int count = 0;
        for (Conversation conversation : conversations) {
            ConversationMember member = this.getMember(user, conversation);
            List<Date> stateChangingDates = stateChangingLogRepository.findAllDateByMember(member);
            int newMessagesCount = conversationMessageRepository.newMessagesCount(conversation, stateChangingDates, member.getMessagesReadingLog().getDate());
            if (newMessagesCount > 0)
                count += 1;
        }
        return count;
    }


    private MessagesReadingLog getReadingLog(ConversationMember member) {
        return readingLogRepository.findOneByMemberId(member.getId());
    }

    private List<ConversationMessage> getMessages(Conversation conversation, User user) {
        ConversationMember member = this.getMember(user, conversation);
        List<Date> stateChangingDates = stateChangingLogRepository.findAllDateByMember(member);
        return conversationMessageRepository.findAllByConversationAndDatesList(conversation, stateChangingDates);

    }

    private List<User> getMembers(Conversation conversation) {
        List<User> members = new ArrayList<>();

        for (ConversationMember member :
                conversationMemberRepository.
                        findByConversationIdAndActiveStatus(conversation.getId(), ActiveStatusName.ACTIVE.getCode()))
            members.add(member.getUser());

        return members;
    }

    private void addStateChangingLog(ConversationMember member) {
        UserStateChangingLog stateChangingLog = new UserStateChangingLog();
        stateChangingLog.setMember(member);
        stateChangingLogRepository.save(stateChangingLog);
    }
}