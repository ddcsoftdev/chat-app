package com.chatapp.message.entity;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.entity.ChatUser;

import java.time.LocalDateTime;

public class MessageBuilder {
    private Long id;
    private String content;
    private Conversation conversation;
    private ChatUser user;
    private LocalDateTime postedAt;
    private LocalDateTime editedAt;

    public MessageBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public MessageBuilder content(String content) {
        this.content = content;
        return this;
    }

    public MessageBuilder conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public MessageBuilder user(ChatUser user) {
        this.user = user;
        return this;
    }

    public MessageBuilder postedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
        return this;
    }

    public MessageBuilder editedAt(LocalDateTime editedAt) {
        this.editedAt = editedAt;
        return this;
    }

    public Message build() {
        return new Message(id, content, conversation, user, postedAt, editedAt);
    }
}