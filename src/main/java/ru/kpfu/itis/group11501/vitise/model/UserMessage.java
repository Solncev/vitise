package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Наталья on 05.04.2017.
 */
@Entity
@Table(name = "users_messages")
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private String message;

    @ManyToOne
    @JoinColumn(name = "communication_relationsid")
    private CommunicationRelation communicationRelation;

    @ManyToOne
    @JoinColumn(name = "usersid")
    private User user;

    public UserMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommunicationRelation getCommunicationRelation() {
        return communicationRelation;
    }

    public void setCommunicationRelation(CommunicationRelation communicationRelation) {
        this.communicationRelation = communicationRelation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
