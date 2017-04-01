package ru.kpfu.itis.group11501.vitise.model;

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

    @Column(name = "is_active", insertable = false)
    private Boolean isActive;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}