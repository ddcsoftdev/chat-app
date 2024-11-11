package com.chatapp.conversation.repository;

import com.chatapp.conversation.entity.Conversation;
import com.chatapp.user.entity.ChatUser;

import java.util.List;
import java.util.Optional;

public interface ConversationDAO {

    List<Conversation> selectAllConversations();

    List<Conversation> selectAllConversationsByUserId(Long userId);

    Optional<Conversation> selectConversationById(Long id);

    Optional<Conversation> selectConversationByUserId(Long userId);

    boolean existsConversationWithId(Long id);

    boolean existsConversationByUserId(Long userId);

    void insertConversation(Conversation conversation);

    void deleteConversation(Long id);

    void updateConversation(Conversation update);
}
