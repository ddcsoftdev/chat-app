package com.chatapp.conversation.repository;

import com.chatapp.conversation.entity.Conversation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository()
public class ConversationJPARepositoryAdapter implements ConversationDAO {
    ConversationRepository conversationRepository;

    public ConversationJPARepositoryAdapter(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<Conversation> selectAllConversations() {
        return conversationRepository.findAll();
    }

    @Override
    public List<Conversation> selectAllConversationsByUserId(Long userId) {
        return conversationRepository.findAllByUsers_Id(userId);
    }

    @Override
    public Optional<Conversation> selectConversationById(Long id) {
        return conversationRepository.findById(id);
    }

    @Override
    public Optional<Conversation> selectConversationByUserId(Long userId) {
        return conversationRepository.findFirstByUsers_Id(userId);
    }

    @Override
    public boolean existsConversationWithId(Long id) {
        return conversationRepository.existsConversationById(id);
    }

    @Override
    public boolean existsConversationByUserId(Long userId) {
        return conversationRepository.existsByUsers_Id(userId);
    }

    @Override
    public void insertConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    @Override
    public void deleteConversation(Long id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public void updateConversation(Conversation update) {
        conversationRepository.save(update);
    }
}
