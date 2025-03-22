package com.chatroom.service;

import com.chatroom.model.ChatMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatMessageTest {
    
    @Test
    void testMessageCreation() {
        ChatMessage message = new ChatMessage();
        assertNotNull(message, "Message should be created");
        assertTrue(message.getTimestamp() > 0, "Timestamp should be set on creation");
    }

    @Test
    void testMessageContent() {
        ChatMessage message = new ChatMessage();
        String testContent = "Hello World";
        message.setContent(testContent);
        assertEquals(testContent, message.getContent(), "Content should match what was set");
    }

    @Test
    void testMessageSender() {
        ChatMessage message = new ChatMessage();
        String sender = "John";
        message.setSender(sender);
        assertEquals(sender, message.getSender(), "Sender should match what was set");
    }

    @Test
    void testTimestampModification() {
        ChatMessage message = new ChatMessage();
        long newTimestamp = 1234567890L;
        message.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, message.getTimestamp(), "Should be able to modify timestamp");
    }

    @Test
    void testEmptyContent() {
        ChatMessage message = new ChatMessage();
        assertNull(message.getContent(), "Content should be null initially");
    }

    @Test
    void testEmptySender() {
        ChatMessage message = new ChatMessage();
        assertNull(message.getSender(), "Sender should be null initially");
    }

    @Test
    void testMessageChaining() {
        ChatMessage message = new ChatMessage();
        String content = "Test content";
        String sender = "Test sender";
        long timestamp = System.currentTimeMillis();

        message.setContent(content)
               .setSender(sender)
               .setTimestamp(timestamp);

        assertEquals(content, message.getContent());
        assertEquals(sender, message.getSender());
        assertEquals(timestamp, message.getTimestamp());
    }

    @Test
    void testMessageEquality() {
        ChatMessage message1 = new ChatMessage();
        ChatMessage message2 = new ChatMessage();

        message1.setContent("Same content");
        message2.setContent("Same content");
        message1.setSender("Same sender");
        message2.setSender("Same sender");
        message1.setTimestamp(1234567890L);
        message2.setTimestamp(1234567890L);

        assertEquals(message1, message2, "Messages with same content should be equal");
    }
} 