package com.chatapp.message.repository;


import com.chatapp.message.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageJPARepositoryAdapter implements MessageDAO{
    MessageRepository messageRepository;

    public MessageJPARepositoryAdapter(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> selectAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> selectMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public boolean existsMessageWithId(Long id) {
        return messageRepository.existsMessageById(id);
    }

    @Override
    public void insertMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void updateMessage(Message update) {
        messageRepository.save(update);
    }
}
