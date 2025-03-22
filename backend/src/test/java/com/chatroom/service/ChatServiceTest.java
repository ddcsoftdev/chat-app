package com.chatroom.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.chatroom.model.ChatMessage;

@ExtendWith(MockitoExtension.class)
public class ChatServiceTest {

    @Test
    void testMessageCreation() {
        // Test basic message creation
        String content = "Hello, World!";
        ChatMessage message = new ChatMessage();
        message.setContent(content);
        
        assertEquals(content, message.getContent(), "Message content should match the input");
    }

    @Test
    void testMessageValidation() {
        // Test empty message validation
        ChatMessage emptyMessage = new ChatMessage();
        assertFalse(isValidMessage(emptyMessage), "Empty message should be invalid");

        // Test valid message
        ChatMessage validMessage = new ChatMessage();
        validMessage.setContent("Valid message");
        assertTrue(isValidMessage(validMessage), "Message with content should be valid");
    }

    private boolean isValidMessage(ChatMessage message) {
        return message != null && message.getContent() != null && !message.getContent().trim().isEmpty();
    }
} 