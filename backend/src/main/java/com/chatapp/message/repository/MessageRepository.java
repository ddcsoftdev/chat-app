package com.chatapp.message.repository;

import com.chatapp.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    boolean existsMessageById(Long id);
}
