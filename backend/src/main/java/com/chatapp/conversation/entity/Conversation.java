package com.chatapp.conversation.entity;

import com.chatapp.message.entity.Message;
import com.chatapp.user.entity.ChatUser;
import jakarta.persistence.*;
import org.jilt.Builder;

import java.util.Set;

@Builder
@Entity
@Table(name="conversation")
public class Conversation {
    @Id
    @SequenceGenerator(
            name = "conversation_id_seq",
            sequenceName = "conversation_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conversation_id_seq"
    )
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "chat_user_conversations",
            joinColumns = @JoinColumn(name = "conversation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<ChatUser> users;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages;

    public Conversation() {

    }

    public Conversation(Long id) {
        this.id = id;
    }

    public Conversation(Long id, Set<ChatUser> users, Set<Message> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

    public Conversation(Set<ChatUser> users, Set<Message> messages) {
        this.users = users;
        this.messages = messages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<ChatUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ChatUser> users) {
        this.users = users;
    }

    public Set<Message> getMessages() {return messages;}

    public void setMessages(Set<Message> messages) {this.messages = messages;}
}
