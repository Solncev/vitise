package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Марат on 22.03.2017.
 */
@Entity
@Table(name = "newses")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private String text;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "is_personal")
    private boolean isPersonal;

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        this.isPersonal = personal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
