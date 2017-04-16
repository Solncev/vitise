package ru.kpfu.itis.group11501.vitise.model;

import javax.persistence.*;

/**
 * Created by Марат on 10.04.2017.
 */
@Entity
@Table(name = "users_messages_files")
public class UserMessageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "users_messages_id")
    private UserMessage userMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }
}
