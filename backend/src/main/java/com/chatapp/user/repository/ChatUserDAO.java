package com.chatapp.user.repository;

import com.chatapp.user.entity.ChatUser;

import java.util.List;
import java.util.Optional;

public interface ChatUserDAO {

    List<ChatUser> selectAllUsers();

    Optional<ChatUser> selectUserById(Long id);

    boolean existsUserWithEmail(String email);

    boolean existsUserWithId(Long id);

    void insertUser(ChatUser user);

    void deleteUser(Long id);

    void updateUser(ChatUser update);

    Optional<ChatUser> selectUserByEmail(String email);
}
