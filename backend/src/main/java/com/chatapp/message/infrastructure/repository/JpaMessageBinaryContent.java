package com.chatapp.message.infrastructure.repository;

import com.chatapp.message.infrastructure.entity.MessageContentBinaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageBinaryContent extends JpaRepository<MessageContentBinaryEntity, Long> {
}

