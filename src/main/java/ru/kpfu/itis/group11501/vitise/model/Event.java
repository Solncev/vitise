package ru.kpfu.itis.group11501.vitise.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Author: Svintenok Kate
 * Date: 22.03.2017
 * Group: 11-501
 * Project: vITISe
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String text;

    @Column(name = "pub_date", insertable = false)
    private Date pubDate;

    @Column(name = "event_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date eventDate;

    @Column(name = "is_public")
    private Boolean isPublic;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Transient
    private Long subscriptionsCount;

    @Transient
    private boolean subscribeStatus = false;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getSubscriptionsCount() {
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(Long subscriptionsCount) {
        this.subscriptionsCount = subscriptionsCount;
    }

    public boolean isSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(boolean subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }
}