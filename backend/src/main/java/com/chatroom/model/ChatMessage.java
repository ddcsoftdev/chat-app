package com.chatroom.model;

import java.util.Objects;

public class ChatMessage {
    private String content;
    private String sender;
    private long timestamp;

    public ChatMessage() {
        this.timestamp = System.currentTimeMillis();
    }

    public String getContent() {
        return content;
    }

    public ChatMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public ChatMessage setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ChatMessage setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return timestamp == that.timestamp &&
               Objects.equals(content, that.content) &&
               Objects.equals(sender, that.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sender, timestamp);
    }
} 