package com.chatapp.conversation.repository;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.entity.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    boolean existsByUsers_Id(Long userId);

    boolean existsConversationById(Long id);

    List<Conversation> findAllByUsers_Id(Long userId);

    Optional<Conversation> findFirstByUsers_Id(Long userId);
}
