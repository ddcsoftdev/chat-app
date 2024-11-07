package com.chatapp.user.entity;

import com.chatapp.user.enums.EAuthRoles;

public class ChatUserBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;
    private EAuthRoles role;
   // private Set<Conversation> conversations;
    public static ChatUserBuilder conversation() {
        return new ChatUserBuilder();
    }

    public ChatUserBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ChatUserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ChatUserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ChatUserBuilder nickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ChatUserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ChatUserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public ChatUserBuilder role(EAuthRoles role) {
        this.role = role;
        return this;
    }
/*
    public ChatUserBuilder conversations(Set<Conversation> conversations) {
        this.conversations = conversations;
        return this;
    }*/

    public ChatUser build() {
        return new ChatUser(
                id,
                firstName,
                lastName,
                nickname,
                email,
                password,
                role.getRoleName()
                //conversations
        );
    }
}
