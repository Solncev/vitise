package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
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

    @Transient
    private List<User> members;

    @Transient
    private List<ConversationMessage> messages;

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
}