package ru.kpfu.itis.group11501.vitise.model.conversation;

import ru.kpfu.itis.group11501.vitise.model.User;
import ru.kpfu.itis.group11501.vitise.model.conversation.enums.ActiveStatusName;

import javax.persistence.*;

/**
 * Author: Svintenok Kate
 * Date: 27.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "conversation_user_list")
public class ConversationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Conversation.class)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private Conversation conversation;

    @Column(name = "active_status", insertable = false)
    private int activeStatus;

    @Transient
    private MessagesReadingLog messagesReadingLog;

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

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(ActiveStatusName statusName) {
        this.activeStatus = statusName.getCode();
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public ActiveStatusName getActiveStatusName() {
        return ActiveStatusName.get(activeStatus);
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public MessagesReadingLog getMessagesReadingLog() {
        return messagesReadingLog;
    }

    public void setMessagesReadingLog(MessagesReadingLog messagesReadingLog) {
        this.messagesReadingLog = messagesReadingLog;
    }
}