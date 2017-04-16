package ru.kpfu.itis.group11501.vitise.model.conversation;

import javax.persistence.*;

/**
 * Created by Марат on 10.04.2017.
 */
@Entity
@Table(name = "conversation_messages_files")
public class ConversationMessageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    @ManyToOne
    @JoinColumn(name = "conversation_messages_id")
    private ConversationMessage conversationMessage;

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

    public ConversationMessage getConversationMessage() {
        return conversationMessage;
    }

    public void setConversationMessage(ConversationMessage conversationMessage) {
        this.conversationMessage = conversationMessage;
    }
}
