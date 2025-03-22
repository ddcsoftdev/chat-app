package com.chatroom.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;

public class ChatUserTest {

    @Test
    void testUserCreation() {
        ChatUser user = new ChatUser("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testUserStatus() {
        ChatUser user = new ChatUser("testUser");
        user.setStatus("ONLINE");
        assertEquals("ONLINE", user.getStatus());
    }

    @Test
    void testLastSeen() {
        ChatUser user = new ChatUser("testUser");
        long currentTime = System.currentTimeMillis();
        user.setLastSeen(currentTime);
        assertEquals(currentTime, user.getLastSeen());
    }

    @Test
    void testUserProfile() {
        ChatUser user = new ChatUser("testUser");
        user.setEmail("test@example.com");
        user.setDisplayName("Test User");
        
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test User", user.getDisplayName());
    }
} 