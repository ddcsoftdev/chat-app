package com.chatapp.message.repository;

import com.chatapp.message.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDAO {
    List<Message> selectAllMessages();

    Optional<Message> selectMessageById(Long id);

    boolean existsMessageWithId(Long id);

    void insertMessage(Message message);

    void deleteMessage(Long id);

    void updateMessage(Message update);
}
