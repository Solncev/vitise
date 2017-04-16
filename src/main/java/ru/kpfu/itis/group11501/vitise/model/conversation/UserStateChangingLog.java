package ru.kpfu.itis.group11501.vitise.model.conversation;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 01.04.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "users_state_changing_log")
public class UserStateChangingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ConversationMember.class)
    @JoinColumn(name = "conversation_member_id", referencedColumnName = "id")
    private ConversationMember member;

    @Column(name = "date", insertable = false)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConversationMember getMember() {
        return member;
    }

    public void setMember(ConversationMember member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}