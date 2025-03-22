package com.chatroom.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ConversationTest {

    @Test
    void testConversationCreation() {
        Conversation conversation = new Conversation();
        assertNotNull(conversation);
        assertNotNull(conversation.getMessages(), "Messages list should be initialized");
    }

    @Test
    void testAddMessage() {
        Conversation conversation = new Conversation();
        ChatMessage message = new ChatMessage();
        message.setContent("Hello");
        
        conversation.addMessage(message);
        assertEquals(1, conversation.getMessages().size());
        assertEquals("Hello", conversation.getMessages().get(0).getContent());
    }

    @Test
    void testConversationParticipants() {
        Conversation conversation = new Conversation();
        ChatUser user1 = new ChatUser("user1");
        ChatUser user2 = new ChatUser("user2");
        
        conversation.addParticipant(user1);
        conversation.addParticipant(user2);
        
        assertEquals(2, conversation.getParticipants().size());
        assertTrue(conversation.getParticipants().contains(user1));
        assertTrue(conversation.getParticipants().contains(user2));
    }

    @Test
    void testConversationTitle() {
        Conversation conversation = new Conversation();
        String title = "Test Chat";
        conversation.setTitle(title);
        assertEquals(title, conversation.getTitle());
    }
} 