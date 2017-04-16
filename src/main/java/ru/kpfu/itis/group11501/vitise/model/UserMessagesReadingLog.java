package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 09.04.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "users_message_reading_log")
public class UserMessagesReadingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = CommunicationRelation.class)
    @JoinColumn(name = "communication_relation_id", referencedColumnName = "id")
    private CommunicationRelation communicationRelation;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "date", insertable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}