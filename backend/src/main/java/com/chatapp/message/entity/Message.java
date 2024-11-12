package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import jakarta.persistence.*;
import org.jilt.Builder;

@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_id_seq",
            sequenceName = "message_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_id_seq"
    )
    private Long id;
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    public Message() {
    }

    public Message(Long id, String content, Conversation conversation) {
        this.id = id;
        this.content = content;
        this.conversation = conversation;
    }

    public Message(String content, Conversation conversation) {
        this.content = content;
        this.conversation = conversation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
