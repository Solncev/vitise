package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Наталья on 05.04.2017.
 */
@Entity
@Table(name = "communication_relations")
public class CommunicationRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_first")
    private User first;

    @ManyToOne
    @JoinColumn(name = "id_second")
    private User second;

    @Column(name = "creation_date", insertable = false)
    private Date creationDate;

    @Transient
    private List<UserMessage> messages;

    @Transient
    private UserMessage lastMessage;

    @Transient
    private int newMessagesCount;

    @Transient
    private UserMessagesReadingLog readingLog;

    @Transient
    private User currentUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFirst() {
        return first;
    }

    public void setFirst(User first) {
        this.first = first;
    }

    public User getSecond() {
        return second;
    }

    public void setSecond(User second) {
        this.second = second;
    }

    public List<UserMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<UserMessage> messages) {
        this.messages = messages;
    }

    public UserMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(UserMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getNewMessagesCount() {
        return newMessagesCount;
    }

    public void setNewMessagesCount(int newMessagesCount) {
        this.newMessagesCount = newMessagesCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserMessagesReadingLog getReadingLog() {
        return readingLog;
    }

    public void setReadingLog(UserMessagesReadingLog readingLog) {
        this.readingLog = readingLog;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
