package com.chatapp.user.repository;

import com.chatapp.user.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    boolean existsUserByEmail(String email);

    boolean existsUserById(Long id);

    Optional<ChatUser> findUserByEmail(String email);
}
