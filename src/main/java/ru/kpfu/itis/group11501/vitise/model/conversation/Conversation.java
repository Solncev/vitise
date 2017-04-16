package ru.kpfu.itis.group11501.vitise.model.conversation;

import ru.kpfu.itis.group11501.vitise.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "conversation")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "creation_date", insertable = false)
    private Date creationDate;

    @Transient
    private List<User> members;

    @Transient
    private List<ConversationMessage> messages;

    @Transient
    private ConversationMessage lastMessage;

    @Transient
    private int newMessagesCount;

    @Transient
    private ConversationMember currentMember;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<ConversationMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ConversationMessage> messages) {
        this.messages = messages;
    }

    public ConversationMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(ConversationMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getNewMessagesCount() {
        return newMessagesCount;
    }

    public void setNewMessagesCount(int newMessagesCount) {
        this.newMessagesCount = newMessagesCount;
    }

    public ConversationMember getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(ConversationMember currentMember) {
        this.currentMember = currentMember;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}