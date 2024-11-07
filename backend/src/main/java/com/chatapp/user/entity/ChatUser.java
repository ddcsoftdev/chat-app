package com.chatapp.user.entity;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.utils.EAuthRoles;
import jakarta.persistence.*;
import org.jilt.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chat_user")
public class ChatUser implements UserDetails {
    @Id()
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long id;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "second_name", nullable = false)
    private String lastName;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "authority", nullable = false)
    private String role;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "chat_user_conversations",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id", referencedColumnName = "id")}
    )
    private Set<Conversation> conversations;

    public ChatUser() {
        this.id = 0L;
        this.firstName = "N/A";
        this.lastName = "N/A";
        this.nickname = "N/A";
        this.email = "n_a@example.com";
        this.password = "password";
        this.conversations = new HashSet<>();
    }

    public ChatUser(String firstName, String lastName, String nickname, String email, String password, EAuthRoles role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role.toString();
        this.conversations = new HashSet<>();
    }

    public ChatUser(String firstName, String lastName, String nickname, String email, String password, String role, Set<Conversation> conversations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.conversations = conversations;
    }

    public ChatUser(Long id, String firstName, String lastName, String nickname, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.conversations = new HashSet<>();
    }

    public ChatUser(Long id, String firstName, String lastName, String nickname, String email, String password, EAuthRoles role, Set<Conversation> conversations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role.toString();
        this.conversations = conversations;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public EAuthRoles getRole() {
        return EAuthRoles.valueOf(this.role);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Conversation> getConversations() {
        return conversations;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setConversations(Set<Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
