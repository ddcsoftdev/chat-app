package com.chatroom.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Conversation {
    private String title;
    private List<ChatMessage> messages;
    private Set<ChatUser> participants;

    public Conversation() {
        this.messages = new ArrayList<>();
        this.participants = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(ChatMessage message) {
        this.messages.add(message);
    }

    public Set<ChatUser> getParticipants() {
        return participants;
    }

    public void addParticipant(ChatUser user) {
        this.participants.add(user);
    }
} 