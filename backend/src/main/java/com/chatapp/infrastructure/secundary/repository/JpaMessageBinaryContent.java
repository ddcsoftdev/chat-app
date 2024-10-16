package com.chatapp.infrastructure.secundary.repository;

import com.chatapp.infrastructure.secundary.entity.MessageContentBinaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMessageBinaryContent extends JpaRepository<MessageContentBinaryEntity, Long> {
}

