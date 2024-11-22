package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.entity.ChatUser;
import jakarta.persistence.*;
import org.apache.commons.lang3.CharUtils;
import org.jilt.Builder;


import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private ChatUser user;

    @Column(name = "posted_at", nullable = false)
    private LocalDateTime postedAt;
    @Column(name = "edited_at")
    private LocalDateTime editedAt;


    public Message() {
    }

    public Message(Long id, String content, Conversation conversation, ChatUser user, LocalDateTime postedAt, LocalDateTime editedAt) {
        this.id = id;
        this.content = content;
        this.conversation = conversation;
        this.user = user;
        this.postedAt = postedAt;
        this.editedAt = editedAt;
    }

    public Message(String content, Conversation conversation, ChatUser user, LocalDateTime postedAt) {
        this.content = content;
        this.conversation = conversation;
        this.user = user;
        this.postedAt = postedAt;
    }

    public Message(String content, Conversation conversation, ChatUser user, LocalDateTime postedAt, LocalDateTime editedAt) {
        this.content = content;
        this.conversation = conversation;
        this.user = user;
        this.postedAt = postedAt;
        this.editedAt = editedAt;
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

    public ChatUser getUser() {
        return user;
    }

    public void setUser(ChatUser user) {
        this.user = user;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
    }
}
