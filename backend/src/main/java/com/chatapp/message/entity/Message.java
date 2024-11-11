package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
