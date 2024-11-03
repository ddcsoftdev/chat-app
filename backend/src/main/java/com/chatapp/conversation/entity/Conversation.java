package com.chatapp.conversation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jilt.Builder;

@Builder
@Entity
@Table(name="conversation")
public class Conversation {
    @Id
    private Long id;

    public Conversation() {

    }

    public Conversation(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
