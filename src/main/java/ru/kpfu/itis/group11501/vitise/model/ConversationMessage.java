package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "conversation_messages")
public class ConversationMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Conversation.class)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation conversation;

    @Column
    private String text;

    @Column(name = "date", insertable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}